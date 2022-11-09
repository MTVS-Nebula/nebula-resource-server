package com.nebula.nebula_resource.app.service.inventory;

import com.nebula.nebula_resource.app.dto.inventory.InventoryDTO;
import com.nebula.nebula_resource.app.dto.inventory.InventoryEquipmentDTO;

public interface EquipmentService {
    InventoryDTO getEquipment(String avatarName);
    void saveEquipment(String avatarName, InventoryEquipmentDTO inventoryEquipmentDTO);
}
