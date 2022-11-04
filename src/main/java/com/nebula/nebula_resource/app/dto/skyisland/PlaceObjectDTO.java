package com.nebula.nebula_resource.app.dto.skyisland;

public class PlaceObjectDTO {
    private String placedObjectTypeSOName;
    private PlaceObjectOriginDTO origin;
    private int dir;

    public PlaceObjectDTO() {
    }

    public PlaceObjectDTO(String placedObjectTypeSOName, PlaceObjectOriginDTO origin, int dir) {
        this.placedObjectTypeSOName = placedObjectTypeSOName;
        this.origin = origin;
        this.dir = dir;
    }

    public String getPlacedObjectTypeSOName() {
        return placedObjectTypeSOName;
    }

    public void setPlacedObjectTypeSOName(String placedObjectTypeSOName) {
        this.placedObjectTypeSOName = placedObjectTypeSOName;
    }

    public PlaceObjectOriginDTO getOrigin() {
        return origin;
    }

    public void setOrigin(PlaceObjectOriginDTO origin) {
        this.origin = origin;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "PlaceObjectDTO{" +
                "placedObjectTypeSOName='" + placedObjectTypeSOName + '\'' +
                ", origin=" + origin +
                ", dir=" + dir +
                '}';
    }
}
