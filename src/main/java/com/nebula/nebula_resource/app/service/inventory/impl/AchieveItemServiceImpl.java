package com.nebula.nebula_resource.app.service.inventory.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarClothes;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarEquipment;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.Clothes;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarClothesRepository;
import com.nebula.nebula_resource.app.dao.repository.item.ClothesRepository;
import com.nebula.nebula_resource.app.service.inventory.AchieveItemService;
import com.nebula.nebula_resource.helper.permission.PermissionChecker;
import com.nebula.nebula_resource.helper.validation.ValidationChecker;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchieveItemServiceImpl implements AchieveItemService {
    private final AvatarRepository avatarRepository;
    private final AvatarClothesRepository avatarClothesRepository;
    private final ClothesRepository clothesRepository;
    @Autowired
    public AchieveItemServiceImpl(AvatarRepository avatarRepository,
                                  AvatarClothesRepository avatarClothesRepository,
                                  ClothesRepository clothesRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarClothesRepository = avatarClothesRepository;
        this.clothesRepository = clothesRepository;
    }

    @Override
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
    private void addClothesToInventory(Avatar avatar, Clothes clothes){
        int emptySlotNumber = getEmptyClothesSlotNumber(avatar);
        AvatarClothes avatarClothes = new AvatarClothes(0,avatar,clothes,emptySlotNumber);
        avatarClothesRepository.save(avatarClothes);
    }
    private int getEmptyClothesSlotNumber(Avatar avatar){
        List<AvatarEquipment> avatarEquipmentList = avatar.getAvatarEquipmentList();
        Set<Integer> usingSlotNumbers = new HashSet<>();
        for (AvatarEquipment avatarEquipment : avatarEquipmentList){
            usingSlotNumbers.add(avatarEquipment.getSlotNumber());
        }
        if (usingSlotNumbers.size() == 24){
            throw new RuntimeException("아이템 창이 가득 찼습니다");
        }
        int emptySlotNumber = -1;
        for (int index = 0; index < 24; index ++){
            if (!usingSlotNumbers.contains(index)){
                emptySlotNumber = index;
            }
        }
        return emptySlotNumber;
    }
}
