package com.nebula.nebula_resource.app.dto.skyisland;

public class SkyIslandIdDTO {
    int id;

    public SkyIslandIdDTO() {
    }

    public SkyIslandIdDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SkyIslandIdDTO{" +
                "id=" + id +
                '}';
    }
}
