package com.nebula.nebula_resource.app.dto.inventory;

import java.util.List;

public class SlotDTO {
    private List<Integer> AllowedItems;
    private SlotItemDTO item;
    private int amount;

    public SlotDTO() {
    }

    public SlotDTO(List<Integer> allowedItems, SlotItemDTO item, int amount) {
        AllowedItems = allowedItems;
        this.item = item;
        this.amount = amount;
    }

    public List<Integer> getAllowedItems() {
        return AllowedItems;
    }

    public void setAllowedItems(List<Integer> allowedItems) {
        AllowedItems = allowedItems;
    }

    public SlotItemDTO getItem() {
        return item;
    }

    public void setItem(SlotItemDTO item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SlotDTO{" +
                "AllowedItems=" + AllowedItems +
                ", item=" + item +
                ", amount=" + amount +
                '}';
    }
}
