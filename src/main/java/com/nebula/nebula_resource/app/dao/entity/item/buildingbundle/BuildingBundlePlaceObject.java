package com.nebula.nebula_resource.app.dao.entity.item.buildingbundle;

import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.embeddable.BuildingBundlePlaceObjectId;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BB_PO", schema = "item")
public class BuildingBundlePlaceObject {
    @EmbeddedId
    private BuildingBundlePlaceObjectId id;

    public BuildingBundlePlaceObject() {
    }

    public BuildingBundlePlaceObject(BuildingBundlePlaceObjectId id) {
        this.id = id;
    }

    public BuildingBundlePlaceObjectId getId() {
        return id;
    }

    public void setId(BuildingBundlePlaceObjectId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BuildingBundlePlaceObject{" +
                "id=" + id +
                '}';
    }
}
