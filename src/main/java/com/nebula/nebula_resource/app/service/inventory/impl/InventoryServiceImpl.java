package com.nebula.nebula_resource.app.service.inventory.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarClothes;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundleBuff;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.ClothesBuff;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarBuildingBundleRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarClothesRepository;
import com.nebula.nebula_resource.app.dto.inventory.BuffDTO;
import com.nebula.nebula_resource.app.dto.inventory.InventoryDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotItemDTO;
import com.nebula.nebula_resource.app.service.inventory.InventoryService;
import com.nebula.nebula_resource.helper.permission.PermissionChecker;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final AvatarRepository avatarRepository;
    private final AvatarBuildingBundleRepository avatarBuildingBundleRepository;
    private final AvatarClothesRepository avatarClothesRepository;
    private static final int INVENTORY_SLOT_NUMBER = 24;
    private static final List<Integer> BB_ALLOWED_ITEMS = List.of(14);
    private static final List<Integer> CL_ALLOWED_ITEMS = List.of(5,6,7,8,9,10,11,12,13,3);

    @Autowired
    public InventoryServiceImpl(AvatarRepository avatarRepository,
                                AvatarBuildingBundleRepository avatarBuildingBundleRepository,
                                AvatarClothesRepository avatarClothesRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarBuildingBundleRepository = avatarBuildingBundleRepository;
        this.avatarClothesRepository = avatarClothesRepository;
    }

    @Override
    public InventoryDTO getBuildingBundleInventory(String avatarName) {
        // 아바타에 대한 권한 검사
        checkAvatarAuthentication(avatarName);

        List<AvatarBuildingBundle> avatarBuildingBundleList = avatarBuildingBundleRepository.findByAvatarAvatarName(avatarName);
        InventoryDTO result = convertAvatarBuildingBundleToDTO(avatarBuildingBundleList);

        return result;
    }

    @Override
    public int getMoney(String avatarName){
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        PermissionChecker.checkAvatarPermission(avatar);
        return avatar.getMoney();
    }

    private InventoryDTO convertAvatarBuildingBundleToDTO(List<AvatarBuildingBundle> avatarBuildingBundleList){
        InventoryDTO result = new InventoryDTO();

        List<SlotDTO> slotDTOList = new ArrayList<>();
        for (int i = 0 ; i < INVENTORY_SLOT_NUMBER; i++){
            slotDTOList.add(generateBuildindBundleEmptySlotDTO());
        }

        for(AvatarBuildingBundle avatarBuildingBundle : avatarBuildingBundleList){
            int index = avatarBuildingBundle.getSlotNumber();
            String name = avatarBuildingBundle.getBuildingBundle().getName();
            int id = avatarBuildingBundle.getBuildingBundle().getElementId();
            int uid = avatarBuildingBundle.getId();


            SlotItemDTO slotItemDTO = new SlotItemDTO(name, id, uid, new ArrayList<>());
            for (BuildingBundleBuff buildingBundleBuff : avatarBuildingBundle.getBuildingBundle().getBuildingBundleBuffs()){
                BuffDTO buffDTO = convertBuildingBundleBuffToDTO(buildingBundleBuff);
                slotItemDTO.getBuffs().add(buffDTO);
            }
            slotDTOList.get(index).setItem(slotItemDTO);
            slotDTOList.get(index).setAmount(1);
        }

        result.setSlots(slotDTOList);

        return result;
    }


    private SlotDTO generateBuildindBundleEmptySlotDTO(){
        SlotItemDTO itemDTO = new SlotItemDTO("",-1,-1, new ArrayList<>());
        SlotDTO slotDTO = new SlotDTO(BB_ALLOWED_ITEMS,itemDTO,0);

        return slotDTO;
    }

    @Override
    public InventoryDTO getClothesInventory(String avatarName) {
        // 아바타에 대한 권한 검사
        checkAvatarAuthentication(avatarName);

        List<AvatarClothes> avatarClothesList = avatarClothesRepository.findByAvatarAvatarName(avatarName);
        InventoryDTO result = convertAvatarClothesToDTO(avatarClothesList);

        return result;
    }

    private InventoryDTO convertAvatarClothesToDTO(List<AvatarClothes> avatarClothesList){
        InventoryDTO result = new InventoryDTO();

        // 슬롯 초기화
        List<SlotDTO> slotDTOList = new ArrayList<>();
        for (int i = 0; i < INVENTORY_SLOT_NUMBER; i++){
            slotDTOList.add(generateClothesEmptySlotDTO());
        }

        if (avatarClothesList == null){
            result.setSlots(slotDTOList);
            return result;
        }

        //슬롯에 아이템 세팅
        for (AvatarClothes avatarClothes : avatarClothesList){
            int index = avatarClothes.getSlotNumber();
            String name = avatarClothes.getClothes().getBaseClothes().getName();
            int id = avatarClothes.getClothes().getBaseClothes().getElementId();
            int uid = avatarClothes.getClothes().getId();

            SlotItemDTO slotItemDTO = new SlotItemDTO(name, id,uid, new ArrayList<>());
            for (ClothesBuff clothesBuff : avatarClothes.getClothes().getBuffs()){
                BuffDTO buffDTO = convertClothesBuffToDTO(clothesBuff);
                slotItemDTO.getBuffs().add(buffDTO);
            }
            slotDTOList.get(index).setItem(slotItemDTO);
            slotDTOList.get(index).setAmount(1);
        }
        result.setSlots(slotDTOList);

        return result;
    }

    private BuffDTO convertClothesBuffToDTO(ClothesBuff clothesBuff){
        BuffDTO result = new BuffDTO();

        result.setStat(clothesBuff.getBuffStat());
        result.setValue(clothesBuff.getBuffValue());
        result.setMax(clothesBuff.getBuffMax());
        result.setMin(clothesBuff.getBuffMin());

        return result;
    }

    private BuffDTO convertBuildingBundleBuffToDTO(BuildingBundleBuff buildingBundleBuff){
        BuffDTO result = new BuffDTO();

        result.setStat(buildingBundleBuff.getBuffStat());
        result.setValue(buildingBundleBuff.getBuffValue());
        result.setMax(buildingBundleBuff.getBuffMax());
        result.setMin(buildingBundleBuff.getBuffMin());

        return result;
    }

    private SlotDTO generateClothesEmptySlotDTO(){
        SlotItemDTO itemDTO = new SlotItemDTO("",-1,-1,new ArrayList<>());
        SlotDTO slotDTO = new SlotDTO(CL_ALLOWED_ITEMS,itemDTO,0);

        return slotDTO;
    }


    private void checkAvatarAuthentication(String avatarName){
        String username = getContextUsername();
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        System.out.println(username +" "+ avatar.getOwner().getUsername());
        if (!username.equals(avatar.getOwner().getUsername())){
            throw new RuntimeException("토큰이 유효하지 않습니다.");
        }
    }

    private String getContextUsername(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return username;
        } catch (Exception e){
            throw e;
        }
    }
}
