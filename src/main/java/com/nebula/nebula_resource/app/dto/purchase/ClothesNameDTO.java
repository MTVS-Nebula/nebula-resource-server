package com.nebula.nebula_resource.app.dto.purchase;

public class ClothesNameDTO {
    private String clothesName;

    public ClothesNameDTO() {
    }

    public ClothesNameDTO(String clothesName) {
        this.clothesName = clothesName;
    }

    public String getClothesName() {
        return clothesName;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    @Override
    public String toString() {
        return "ClothesNameDTO{" +
                "clothesName='" + clothesName + '\'' +
                '}';
    }
}
