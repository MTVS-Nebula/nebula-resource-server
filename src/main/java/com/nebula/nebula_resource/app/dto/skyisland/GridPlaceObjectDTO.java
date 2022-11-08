package com.nebula.nebula_resource.app.dto.skyisland;

import java.util.List;

public class GridPlaceObjectDTO {
    private List<PlaceObjectDTO> gridPlaceObjectList;

    public GridPlaceObjectDTO() {
    }

    public GridPlaceObjectDTO(List<PlaceObjectDTO> gridPlaceObjectList) {
        this.gridPlaceObjectList = gridPlaceObjectList;
    }

    public List<PlaceObjectDTO> getGridPlaceObjectList() {
        return gridPlaceObjectList;
    }

    public void setGridPlaceObjectList(List<PlaceObjectDTO> gridPlaceObjectList) {
        this.gridPlaceObjectList = gridPlaceObjectList;
    }

    @Override
    public String toString() {
        return "GridPlaceObjectDTO{" +
                "GridPlaceObjectList=" + gridPlaceObjectList +
                '}';
    }
}
