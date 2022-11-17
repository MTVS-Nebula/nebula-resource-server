package com.nebula.nebula_resource.helper.validation;

import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationChecker {
    private static AvatarClothesRepository avatarClothesRepository;
    @Autowired
    private ValidationChecker(AvatarClothesRepository avatarClothesRepository){
        ValidationChecker.avatarClothesRepository = avatarClothesRepository;
    }

    public static void achieveClothesValidation(int uniqueId) {
        int count = avatarClothesRepository.countByClothesId(uniqueId);
        if (count != 0){
            throw new RuntimeException("소유권이 있는 아이템입니다.");
        }
    }
}
