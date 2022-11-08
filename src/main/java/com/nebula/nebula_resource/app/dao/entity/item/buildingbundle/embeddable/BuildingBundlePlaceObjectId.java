package com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.embeddable;

import com.nebula.nebula_resource.app.dao.entity.item.PlaceObject;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundle;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class BuildingBundlePlaceObjectId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "BUILDING_BUNDLE_ID")
    private BuildingBundle buildingBundle;
    @ManyToOne
    @JoinColumn(name = "PLACE_OBJECT_ID")
    private PlaceObject placeObject;

    public BuildingBundlePlaceObjectId() {
    }

    public BuildingBundlePlaceObjectId(BuildingBundle buildingBundle, PlaceObject placeObject) {
        this.buildingBundle = buildingBundle;
        this.placeObject = placeObject;
    }

    public BuildingBundle getBuildingBundle() {
        return buildingBundle;
    }

    public void setBuildingBundle(BuildingBundle buildingBundle) {
        this.buildingBundle = buildingBundle;
    }

    public PlaceObject getPlaceObject() {
        return placeObject;
    }

    public void setPlaceObject(PlaceObject placeObject) {
        this.placeObject = placeObject;
    }

    @Override
    public String toString() {
        return "BuildingBundlePlaceObjectId{" +
                "buildingBundle=" + buildingBundle +
                ", placeObject=" + placeObject +
                '}';
    }
}
