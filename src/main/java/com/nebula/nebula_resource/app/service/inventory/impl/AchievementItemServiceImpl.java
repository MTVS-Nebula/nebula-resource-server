package com.nebula.nebula_resource.app.service.inventory.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.service.inventory.AchieveItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchievementItemServiceImpl implements AchieveItemService {
    private final AvatarRepository avatarRepository;
    @Autowired
    public AchievementItemServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @Override
    public void pickUpClothes(String avatarName, int clothesUniqueId) {
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);

    }
}
