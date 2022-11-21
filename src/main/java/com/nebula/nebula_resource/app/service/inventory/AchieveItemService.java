package com.nebula.nebula_resource.app.service.inventory;

public interface AchieveItemService {
    void pickUpClothes(String avatarName, int clothesUniqueId);
    void dropClothes(String avatarName, int clothesUniqueId);
}
