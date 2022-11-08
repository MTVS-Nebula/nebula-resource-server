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

    public BuildingBundle() {
    }

    public BuildingBundle(int id, String name, List<BuildingBundleBuff> buildingBundleBuffs) {
        this.id = id;
        this.name = name;
        this.buildingBundleBuffs = buildingBundleBuffs;
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

    public List<BuildingBundleBuff> getBuildingBundleBuffs() {
        return buildingBundleBuffs;
    }

    public void setBuildingBundleBuffs(
            List<BuildingBundleBuff> buildingBundleBuffs) {
        this.buildingBundleBuffs = buildingBundleBuffs;
    }

    @Override
    public String toString() {
        return "BuildingBundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buildingBundleBuffs=" + buildingBundleBuffs +
                '}';
    }
}