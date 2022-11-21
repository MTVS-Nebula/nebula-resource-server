package com.nebula.nebula_resource.app.service.inventory;

import com.nebula.nebula_resource.app.dto.inventory.SlotItemDTO;
import org.springframework.transaction.annotation.Transactional;

public interface AchieveItemService {
    void pickUpClothes(String avatarName, int clothesUniqueId);
    void dropClothes(String avatarName, int clothesUniqueId);
    SlotItemDTO generateNewClothes(String avatarName);
}
