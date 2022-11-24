package com.nebula.nebula_resource.app.service.purchase.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.item.rotation.ClothesRotation;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarBuildingBundleRepository;
import com.nebula.nebula_resource.app.dao.repository.item.BuildingBundleRepository;
import com.nebula.nebula_resource.app.dao.repository.item.rotation.ClothesRotationRepository;
import com.nebula.nebula_resource.app.dto.inventory.SlotItemDTO;
import com.nebula.nebula_resource.app.dto.purchase.PurchaseBuildingBundleDTO;
import com.nebula.nebula_resource.app.dto.purchase.PurchaseInfoDTO;
import com.nebula.nebula_resource.app.dto.purchase.PurchaseListDTO;
import com.nebula.nebula_resource.app.service.purchase.PurchaseService;
import com.nebula.nebula_resource.helper.exception.ItemExistException;
import com.nebula.nebula_resource.helper.exception.LackMoneyException;
import com.nebula.nebula_resource.helper.exception.NotAvailableException;
import com.nebula.nebula_resource.helper.permission.PermissionChecker;
import com.nebula.nebula_resource.helper.exception.NoObjectException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final AvatarRepository avatarRepository;
    private final BuildingBundleRepository buildingBundleRepository;
    private final AvatarBuildingBundleRepository avatarBuildingBundleRepository;
    private final ClothesRotationRepository clothesRotationRepository;

    @Autowired
    public PurchaseServiceImpl(AvatarRepository avatarRepository, BuildingBundleRepository buildingBundleRepository,
                               AvatarBuildingBundleRepository avatarBuildingBundleRepository, ClothesRotationRepository clothesRotationRepository) {
        this.avatarRepository = avatarRepository;
        this.buildingBundleRepository = buildingBundleRepository;
        this.avatarBuildingBundleRepository = avatarBuildingBundleRepository;
        this.clothesRotationRepository = clothesRotationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseListDTO getPurchaseList(String avatarName){
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        PermissionChecker.checkAvatarPermission(avatar);
        List<BuildingBundle> purchasableBBList = getPurchasableBuildingBundleList(avatar);
        List<PurchaseInfoDTO> purchasableBBInfoList= convertBuildingBundleListToDTO(purchasableBBList);
        List<ClothesRotation> clothesRotationList = clothesRotationRepository.findBy();
        List<PurchaseInfoDTO> purchasableCLInfoList = convertClothesRotationToDTO(clothesRotationList);
        PurchaseListDTO result = new PurchaseListDTO(purchasableCLInfoList, purchasableBBInfoList);
        return result;
    }


    private List<PurchaseInfoDTO> convertBuildingBundleListToDTO(List<BuildingBundle> buildingBundleList){
        List<PurchaseInfoDTO> result = new ArrayList<>();
        for (BuildingBundle buildingBundle : buildingBundleList){
            int id = buildingBundle.getElementId();
            String name = buildingBundle.getName();
            int price = buildingBundle.getPurchaseAmount();
            result.add(new PurchaseInfoDTO(id,name,price));
        }
        return result;
    }

    private List<BuildingBundle> getPurchasableBuildingBundleList(Avatar avatar){
        List<BuildingBundle> result = new ArrayList<>();
        List<BuildingBundle> totalBundleList = buildingBundleRepository.findAll();
        Set<Integer> ownedBundleIdSet = getOwnedBundleIdSet(avatar);
        for (BuildingBundle buildingBundle : totalBundleList){
            if (!ownedBundleIdSet.contains(buildingBundle.getElementId())){
                result.add(buildingBundle);
            }
        }
        return result;
    }

    private Set<Integer> getOwnedBundleIdSet(Avatar avatar){
        Set<Integer> result = new HashSet<>();
        List<AvatarBuildingBundle> avatarBuildingBundleList = avatar.getAvatarBuildingBundleList();
        for (AvatarBuildingBundle avatarBuildingBundle : avatarBuildingBundleList){
            result.add(avatarBuildingBundle.getBuildingBundle().getElementId());
        }
        return result;
    }

    private List<PurchaseInfoDTO> convertClothesRotationToDTO(List<ClothesRotation> clothesRotationList){
        List<PurchaseInfoDTO> result = new ArrayList<>();
        for (ClothesRotation clothesRotation : clothesRotationList){
            int id = clothesRotation.getClothes().getElementId();
            String name = clothesRotation.getClothes().getName();
            int price = clothesRotation.getClothes().getPurchaseAmount();
            result.add(new PurchaseInfoDTO(id,name,price));
        }
        return result;
    }

    @Override
    @Transactional
    public PurchaseBuildingBundleDTO purchaseBundle(String avatarName, String bundleName) {
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        PermissionChecker.checkAvatarPermission(avatar);
        BuildingBundle buildingBundle = findBuildingBundleByName(bundleName);
        checkBundleExists(avatar, buildingBundle);
        int remainMoney = purchase(avatar, buildingBundle.getPurchaseAmount());
        if (buildingBundle.getPurchaseAmount() == null){
            throw new NotAvailableException("구매 불가한 번들입니다.");
        }
        AvatarBuildingBundle avatarBuildingBundle = new AvatarBuildingBundle(0,avatar,buildingBundle,
                buildingBundle.getFixSlotNumber());
        avatarBuildingBundleRepository.save(avatarBuildingBundle);
        return convertToPurchaseBuildingBundleDTO(avatarBuildingBundle, remainMoney);
    }

    private BuildingBundle findBuildingBundleByName(String bundleName){
        BuildingBundle buildingBundle = buildingBundleRepository.findByName(bundleName);
        if (buildingBundle == null){
            throw new NoObjectException("해당하는 이름의 번들이 없습니다.");
        }
        return buildingBundle;
    }

    private void checkBundleExists(Avatar avatar, BuildingBundle buildingBundle){
        AvatarBuildingBundle avatarBuildingBundle =
                avatarBuildingBundleRepository.findByAvatarAndBuildingBundle(avatar, buildingBundle);
        if (avatarBuildingBundle != null){
            throw new ItemExistException("번들을 이미 소유하고 있습니다.");
        }
    }

    private int purchase(Avatar avatar, int amount){
        if (avatar.getMoney() < amount){
            throw new LackMoneyException("구매를 위한 금액이 부족합니다.");
        }
        int remainMoney = avatar.getMoney() - amount;
        avatar.setMoney(remainMoney);
        avatarRepository.save(avatar);

        return remainMoney;
    }

    private PurchaseBuildingBundleDTO convertToPurchaseBuildingBundleDTO(AvatarBuildingBundle avatarBuildingBundle, int remainMoney){
        BuildingBundle buildingBundle = avatarBuildingBundle.getBuildingBundle();
        SlotItemDTO slotItemDTO =
                new SlotItemDTO(buildingBundle.getName(),
                        buildingBundle.getElementId(), avatarBuildingBundle.getId(), new ArrayList<>());
        PurchaseBuildingBundleDTO purchaseBuildingBundleDTO
                = new PurchaseBuildingBundleDTO(buildingBundle.getFixSlotNumber(), remainMoney, slotItemDTO);
        return purchaseBuildingBundleDTO;
    }
}
