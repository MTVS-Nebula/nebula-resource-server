package com.nebula.nebula_resource.app.dto.skyisland;

import java.util.List;

public class IslandPlaceObjectDTO {
    private List<GridPlaceObjectDTO> IslandGridList;

    public IslandPlaceObjectDTO() {
    }

    public IslandPlaceObjectDTO(List<GridPlaceObjectDTO> islandGridList) {
        IslandGridList = islandGridList;
    }

    public List<GridPlaceObjectDTO> getIslandGridList() {
        return IslandGridList;
    }

    public void setIslandGridList(List<GridPlaceObjectDTO> islandGridList) {
        IslandGridList = islandGridList;
    }

    @Override
    public String toString() {
        return "IslandPlaceObjectDTO{" +
                "IslandGridList=" + IslandGridList +
                '}';
    }
}
