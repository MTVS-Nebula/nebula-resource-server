package com.nebula.nebula_resource.app.dto.skyisland;

public class SkyIslandDTO {
    private int id;
    private String owner;
    private IslandPlaceObjectDTO placeObjects;
    private IslandDropItemDTO dropItems;

    public SkyIslandDTO() {
    }

    public SkyIslandDTO(int id, String owner, IslandPlaceObjectDTO placeObjects, IslandDropItemDTO dropItems) {
        this.id = id;
        this.owner = owner;
        this.placeObjects = placeObjects;
        this.dropItems = dropItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public IslandPlaceObjectDTO getPlaceObjects() {
        return placeObjects;
    }

    public void setPlaceObjects(IslandPlaceObjectDTO placeObjects) {
        this.placeObjects = placeObjects;
    }

    public IslandDropItemDTO getDropItems() {
        return dropItems;
    }

    public void setDropItems(IslandDropItemDTO dropItems) {
        this.dropItems = dropItems;
    }

    @Override
    public String toString() {
        return "SkyIslandDTO{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", placeObjects=" + placeObjects +
                ", dropItems=" + dropItems +
                '}';
    }
}
