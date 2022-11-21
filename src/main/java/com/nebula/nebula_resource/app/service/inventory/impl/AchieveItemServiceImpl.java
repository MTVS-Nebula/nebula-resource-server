package com.nebula.nebula_resource.app.service.inventory.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarClothes;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarEquipment;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.Clothes;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.ClothesBuff;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarClothesRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarEquipmentRepository;
import com.nebula.nebula_resource.app.dao.repository.item.ClothesRepository;
import com.nebula.nebula_resource.app.dto.inventory.BuffDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotItemDTO;
import com.nebula.nebula_resource.app.service.inventory.AchieveItemService;
import com.nebula.nebula_resource.helper.generator.RandomClothesGenerator;
import com.nebula.nebula_resource.helper.permission.PermissionChecker;
import com.nebula.nebula_resource.helper.validation.ValidationChecker;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AchieveItemServiceImpl implements AchieveItemService {
    private final AvatarRepository avatarRepository;
    private final AvatarClothesRepository avatarClothesRepository;
    private final AvatarEquipmentRepository avatarEquipmentRepository;
    private final ClothesRepository clothesRepository;
    @Autowired
    public AchieveItemServiceImpl(AvatarRepository avatarRepository,
                                  AvatarClothesRepository avatarClothesRepository,
                                  AvatarEquipmentRepository avatarEquipmentRepository,
                                  ClothesRepository clothesRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarClothesRepository = avatarClothesRepository;
        this.avatarEquipmentRepository = avatarEquipmentRepository;
        this.clothesRepository = clothesRepository;
    }

    @Override
    @Transactional
    public void pickUpClothes(String avatarName, int clothesUniqueId) {
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        PermissionChecker.checkAvatarPermission(avatar);
        ValidationChecker.achieveClothesValidation(clothesUniqueId);
        Clothes clothes = clothesRepository.findById(clothesUniqueId);
        if (clothes == null){
            throw new RuntimeException("옷의 uniqueId가 잘못되었습니다.");
        }
        addClothesToInventory(avatar,clothes);
    }

    @Override
    @Transactional
    public void dropClothes(String avatarName, int clothesUniqueId){
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        PermissionChecker.checkAvatarPermission(avatar);
        AvatarClothes avatarClothes = avatarClothesRepository.findByAvatarAndClothesId(avatar,clothesUniqueId);
        AvatarEquipment avatarEquipment = avatarEquipmentRepository.findByAvatarAndClothesId(avatar,clothesUniqueId);
        if (avatarClothes != null){
            avatarClothesRepository.delete(avatarClothes);
        }
        if (avatarEquipment != null){
            avatarEquipmentRepository.delete(avatarEquipment);
        }
        if (avatarClothes == null && avatarEquipment == null){
            throw new RuntimeException("옷에 대한 소유권이 없습니다.");
        }
    }

    private void addClothesToInventory(Avatar avatar, Clothes clothes){
        int emptySlotNumber = getEmptyClothesSlotNumber(avatar);
        AvatarClothes avatarClothes = new AvatarClothes(0,avatar,clothes,emptySlotNumber);
        avatarClothesRepository.save(avatarClothes);
    }
    private int getEmptyClothesSlotNumber(Avatar avatar){
        List<AvatarClothes> avatarClothesList = avatarClothesRepository.findByAvatar(avatar);
        Set<Integer> usingSlotNumbers = new HashSet<>();
        for (AvatarClothes avatarClothes : avatarClothesList){
            usingSlotNumbers.add(avatarClothes.getSlotNumber());
        }
        if (usingSlotNumbers.size() == 24){
            throw new RuntimeException("아이템 창이 가득 찼습니다");
        }
        for (int index = 0; index < 24; index ++){
            if (!usingSlotNumbers.contains(index)){
                return index;
            }
        }
        throw new RuntimeException("빈 슬롯을 찾지 못했습니다");
    }

    @Override
    @Transactional
    public SlotItemDTO generateNewClothes(String avatarName){
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        PermissionChecker.checkAvatarPermission(avatar);
        Clothes clothes = RandomClothesGenerator.generateClothes(avatar);
        int slotNumber = getEmptyClothesSlotNumber(avatar);
        if (clothes != null){
            AvatarClothes avatarClothes = new AvatarClothes(0,avatar,clothes,slotNumber);
            avatarClothesRepository.save(avatarClothes);
        }
        return convertClothesToDTO(clothes);
    }

    private SlotItemDTO convertClothesToDTO(Clothes clothes){
        SlotItemDTO slotItemDTO = null;
        if (clothes == null){
            slotItemDTO = new SlotItemDTO("꽝", -1, -1, null);
            return slotItemDTO;
        }
        slotItemDTO = new SlotItemDTO(clothes.getBaseClothes().getName(),
                clothes.getBaseClothes().getElementId(), clothes.getId(),
                convertClothesBuffListToDTO(clothes.getBuffs()));
        return slotItemDTO;
    }

    private List<BuffDTO> convertClothesBuffListToDTO(List<ClothesBuff> clothesBuffs){
        List<BuffDTO> result = new ArrayList<>();
        if (clothesBuffs == null){
            return result;
        }
        for(ClothesBuff clothesBuff : clothesBuffs){
            result.add(convertClothesBuffToDTO(clothesBuff));
        }
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

}
