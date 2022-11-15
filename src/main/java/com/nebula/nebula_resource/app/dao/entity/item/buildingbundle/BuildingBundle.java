package com.nebula.nebula_resource.app.dao.entity.item.buildingbundle;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BUILDING_BUNDLE", schema = "item")
@SequenceGenerator(
        name = "SEQ_BUILDING_BUNDLE_ID_GENERATOR",
        sequenceName = "SEQ_BUILDING_BUNDLE_ID",
        initialValue = 1,
        allocationSize = 1
)
public class BuildingBundle {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_BUILDING_BUNDLE_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "buildingBundle")
    private List<BuildingBundleBuff> buildingBundleBuffs;
    @OneToMany(mappedBy = "id.buildingBundle")
    private List<BuildingBundlePlaceObject> buildingBundlePlaceObjects;
    @Column(name = "ELEMENT_ID")
    private Integer elementId;
    @Column(name = "PURCHASE_AMOUNT")
    private Integer purchaseAmount;
    @Column(name = "FIX_SLOT_NUMBER")
    private Integer fixSlotNumber;

    public BuildingBundle() {
    }

    public BuildingBundle(int id, String name, List<BuildingBundleBuff> buildingBundleBuffs,
                          List<BuildingBundlePlaceObject> buildingBundlePlaceObjects, int elementId, int purchaseAmount) {
        this.id = id;
        this.name = name;
        this.buildingBundleBuffs = buildingBundleBuffs;
        this.buildingBundlePlaceObjects = buildingBundlePlaceObjects;
        this.elementId = elementId;
        this.purchaseAmount = purchaseAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public Integer getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Integer purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Integer getFixSlotNumber() {
        return fixSlotNumber;
    }

    public void setFixSlotNumber(Integer fixSlotNumber) {
        this.fixSlotNumber = fixSlotNumber;
    }

    public List<BuildingBundleBuff> getBuildingBundleBuffs() {
        return buildingBundleBuffs;
    }

    public void setBuildingBundleBuffs(
            List<BuildingBundleBuff> buildingBundleBuffs) {
        this.buildingBundleBuffs = buildingBundleBuffs;
    }

    public List<BuildingBundlePlaceObject> getBuildingBundlePlaceObjects() {
        return buildingBundlePlaceObjects;
    }

    public void setBuildingBundlePlaceObjects(
            List<BuildingBundlePlaceObject> buildingBundlePlaceObjects) {
        this.buildingBundlePlaceObjects = buildingBundlePlaceObjects;
    }

    @Override
    public String toString() {
        return "BuildingBundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", elementId=" + elementId +
                ", buildingBundleBuffs=" + buildingBundleBuffs +
                ", buildingBundlePlaceObjects=" + buildingBundlePlaceObjects +
                '}';
    }
}
