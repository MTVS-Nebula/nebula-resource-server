package com.nebula.nebula_resource.app.dto.inventory;

import java.util.List;

public class InventoryDTO {
    private List<SlotDTO> Slots;

    public InventoryDTO() {
    }

    public InventoryDTO(List<SlotDTO> slots) {
        Slots = slots;
    }

    public List<SlotDTO> getSlots() {
        return Slots;
    }

    public void setSlots(List<SlotDTO> slots) {
        Slots = slots;
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "Slots=" + Slots +
                '}';
    }
}
