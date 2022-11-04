package com.nebula.nebula_resource.app.dao.entity.skyisland.embeddable;

import com.nebula.nebula_resource.app.dao.entity.item.PlaceObject;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIsland;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class SkyIslandPlaceObjectId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "SKY_ISLAND_ID")
    private SkyIsland skyIsland;

    @OneToOne
    @JoinColumn(name = "PLACE_OBJECT_ID")
    private PlaceObject placeObject;

    public SkyIslandPlaceObjectId() {
    }

    public SkyIslandPlaceObjectId(SkyIsland skyIsland, PlaceObject placeObject) {
        this.skyIsland = skyIsland;
        this.placeObject = placeObject;
    }

    public SkyIsland getSkyIsland() {
        return skyIsland;
    }

    public void setSkyIsland(SkyIsland skyIsland) {
        this.skyIsland = skyIsland;
    }

    public PlaceObject getPlaceObject() {
        return placeObject;
    }

    public void setPlaceObject(PlaceObject placeObject) {
        this.placeObject = placeObject;
    }

    @Override
    public String toString() {
        return "SkyIslandPlaceObjectId{" +
                "skyIsland=" + skyIsland +
                ", placeObject=" + placeObject +
                '}';
    }
}
