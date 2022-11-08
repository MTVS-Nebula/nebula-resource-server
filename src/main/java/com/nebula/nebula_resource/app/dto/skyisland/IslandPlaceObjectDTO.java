package com.nebula.nebula_resource.app.dto.skyisland;

import java.util.List;

public class IslandPlaceObjectDTO {
    private List<GridPlaceObjectDTO> islandGridList;

    public IslandPlaceObjectDTO() {
    }

    public IslandPlaceObjectDTO(List<GridPlaceObjectDTO> islandGridList) {
        this.islandGridList = islandGridList;
    }

    public List<GridPlaceObjectDTO> getIslandGridList() {
        return islandGridList;
    }

    public void setIslandGridList(List<GridPlaceObjectDTO> islandGridList) {
        this.islandGridList = islandGridList;
    }

    @Override
    public String toString() {
        return "IslandPlaceObjectDTO{" +
                "islandGridList=" + islandGridList +
                '}';
    }
}
