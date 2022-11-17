package com.nebula.nebula_resource.app.dto.inventory;

public class ClothesUniqueIdDTO {
    private int uniqueId;

    public ClothesUniqueIdDTO() {
    }

    public ClothesUniqueIdDTO(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "ClothesUniqueIdDTO{" +
                "uniqueId=" + uniqueId +
                '}';
    }
}
