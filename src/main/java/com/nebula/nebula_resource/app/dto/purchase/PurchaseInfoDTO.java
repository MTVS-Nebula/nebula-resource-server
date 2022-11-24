package com.nebula.nebula_resource.app.dto.purchase;

public class PurchaseInfoDTO {
    private int id;
    private String name;
    private int price;

    public PurchaseInfoDTO() {
    }

    public PurchaseInfoDTO(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ClothesPurchaseInfoDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
