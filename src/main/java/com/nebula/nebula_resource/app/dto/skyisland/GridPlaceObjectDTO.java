package com.nebula.nebula_resource.app.dto.skyisland;

import java.util.List;

public class GridPlaceObjectDTO {
    private List<PlaceObjectDTO> GridPlaceObjectList;

    public GridPlaceObjectDTO() {
    }

    public GridPlaceObjectDTO(List<PlaceObjectDTO> gridPlaceObjectList) {
        GridPlaceObjectList = gridPlaceObjectList;
    }

    public List<PlaceObjectDTO> getGridPlaceObjectList() {
        return GridPlaceObjectList;
    }

    public void setGridPlaceObjectList(List<PlaceObjectDTO> gridPlaceObjectList) {
        GridPlaceObjectList = gridPlaceObjectList;
    }

    @Override
    public String toString() {
        return "GridPlaceObjectDTO{" +
                "GridPlaceObjectList=" + GridPlaceObjectList +
                '}';
    }
}
