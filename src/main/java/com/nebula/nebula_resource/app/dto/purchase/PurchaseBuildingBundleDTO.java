package com.nebula.nebula_resource.app.dto.purchase;

import com.nebula.nebula_resource.app.dto.inventory.SlotItemDTO;

public class PurchaseBuildingBundleDTO {
    private int slotNumber;
    private int remainMoney;
    SlotItemDTO bundle;

    public PurchaseBuildingBundleDTO() {
    }

    public PurchaseBuildingBundleDTO(int slotNumber, int remainMoney, SlotItemDTO bundle) {
        this.slotNumber = slotNumber;
        this.remainMoney = remainMoney;
        this.bundle = bundle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(int remainMoney) {
        this.remainMoney = remainMoney;
    }

    public SlotItemDTO getBundle() {
        return bundle;
    }

    public void setBundle(SlotItemDTO bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString() {
        return "PurchaseBuildingBundleDTO{" +
                "slotNumber=" + slotNumber +
                ", remainMoney=" + remainMoney +
                ", bundle=" + bundle +
                '}';
    }
}
