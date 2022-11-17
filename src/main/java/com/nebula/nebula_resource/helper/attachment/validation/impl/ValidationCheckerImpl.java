package com.nebula.nebula_resource.helper.attachment.validation.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarClothes;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarClothesRepository;
import com.nebula.nebula_resource.helper.attachment.validation.ValidationChecker;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationCheckerImpl implements ValidationChecker {
    private static AvatarClothesRepository avatarClothesRepository;
    @Autowired
    private ValidationCheckerImpl(AvatarClothesRepository avatarClothesRepository){
        ValidationCheckerImpl.avatarClothesRepository = avatarClothesRepository;
    }
    @Override
    public void achieveClothesValidation(int uniqueId) {
        int count = avatarClothesRepository.countByClothesId(uniqueId);
        if (count != 0){
            throw new RuntimeException("소유권이 있는 아이템입니다.");
        }
    }
}
