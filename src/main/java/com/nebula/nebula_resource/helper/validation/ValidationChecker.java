package com.nebula.nebula_resource.helper.validation;

import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarClothesRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationChecker {
    private static AvatarClothesRepository avatarClothesRepository;
    private static AvatarEquipmentRepository avatarEquipmentRepository;
    @Autowired
    private ValidationChecker(AvatarClothesRepository avatarClothesRepository,
                              AvatarEquipmentRepository avatarEquipmentRepository){
        ValidationChecker.avatarClothesRepository = avatarClothesRepository;
        ValidationChecker.avatarEquipmentRepository = avatarEquipmentRepository;
    }

    public static void achieveClothesValidation(int uniqueId) {
        int count = avatarClothesRepository.countByClothesId(uniqueId);
        count += avatarEquipmentRepository.countByClothesId(uniqueId);
        if (count != 0){
            throw new RuntimeException("소유권이 있는 아이템입니다.");
        }
    }
}
