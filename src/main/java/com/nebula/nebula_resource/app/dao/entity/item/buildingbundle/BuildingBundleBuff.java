package com.nebula.nebula_resource.app.dao.entity.item.buildingbundle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BB_BUFF", schema = "item")
@SequenceGenerator(
        name = "SEQ_BB_BUFF_ID_GENERATOR",
        sequenceName = "SEQ_BB_BUFF_ID",
        initialValue = 1,
        allocationSize = 1
)
public class BuildingBundleBuff {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_BB_BUFF_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @Column(name = "BUFF_STAT")
    private int buffStat;
    @Column(name = "BUFF_VALUE")
    private int buffValue;
    @Column(name = "BUFF_MIN")
    private int buffMin;
    @Column(name = "BUFF_MAX")
    private int buffMax;
    @ManyToOne
    @JoinColumn(name = "BUILDING_BUNDLE_ID")
    private BuildingBundle buildingBundle;

    public BuildingBundleBuff() {
    }

    public BuildingBundleBuff(int id, int buffStat, int buffValue, int buffMin, int buffMax,
                              BuildingBundle buildingBundle) {
        this.id = id;
        this.buffStat = buffStat;
        this.buffValue = buffValue;
        this.buffMin = buffMin;
        this.buffMax = buffMax;
        this.buildingBundle = buildingBundle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuffStat() {
        return buffStat;
    }

    public void setBuffStat(int buffStat) {
        this.buffStat = buffStat;
    }

    public int getBuffValue() {
        return buffValue;
    }

    public void setBuffValue(int buffValue) {
        this.buffValue = buffValue;
    }

    public int getBuffMin() {
        return buffMin;
    }

    public void setBuffMin(int buffMin) {
        this.buffMin = buffMin;
    }

    public int getBuffMax() {
        return buffMax;
    }

    public void setBuffMax(int buffMax) {
        this.buffMax = buffMax;
    }

    public BuildingBundle getBuildingBundle() {
        return buildingBundle;
    }

    public void setBuildingBundle(BuildingBundle buildingBundle) {
        this.buildingBundle = buildingBundle;
    }

    @Override
    public String toString() {
        return "BuildingBundleBuff{" +
                "id=" + id +
                ", buffStat=" + buffStat +
                ", buffValue=" + buffValue +
                ", buffMin=" + buffMin +
                ", buffMax=" + buffMax +
                ", buildingBundle=" + buildingBundle +
                '}';
    }
}
