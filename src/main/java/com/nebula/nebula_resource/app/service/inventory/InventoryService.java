package com.nebula.nebula_resource.app.service.inventory;

import com.nebula.nebula_resource.app.dto.inventory.InventoryDTO;

public interface InventoryService {

    InventoryDTO getBuildingBundleInventory(String avatarName);
    InventoryDTO getClothesInventory(String avatarName);
    int getMoney(String avatarName);
}
