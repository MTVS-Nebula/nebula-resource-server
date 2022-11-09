package com.nebula.nebula_resource.app.dto.inventory;

public class InventoryEquipmentDTO {
    private InventoryDTO clothesInventory;
    private InventoryDTO equipment;

    public InventoryEquipmentDTO() {
    }

    public InventoryEquipmentDTO(InventoryDTO clothesInventory, InventoryDTO equipment) {
        this.clothesInventory = clothesInventory;
        this.equipment = equipment;
    }

    public InventoryDTO getClothesInventory() {
        return clothesInventory;
    }

    public void setClothesInventory(InventoryDTO clothesInventory) {
        this.clothesInventory = clothesInventory;
    }

    public InventoryDTO getEquipment() {
        return equipment;
    }

    public void setEquipment(InventoryDTO equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "InventoryEquipmentDTO{" +
                "clothesInventory=" + clothesInventory +
                ", equipment=" + equipment +
                '}';
    }
}
