package com.nebula.nebula_resource.app.dao.entity.inventory;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundle;
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
@Table(name = "TBL_AVATAR_BB", schema = "avatar")
@SequenceGenerator(
        name = "SEQ_AVATAR_BB_ID_GENERATOR",
        sequenceName = "SEQ_AVATAR_BB_ID",
        initialValue = 1,
        allocationSize = 1
)
public class AvatarBuildingBundle {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AVATAR_BB_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "AVATAR_ID")
    private Avatar avatar;
    @ManyToOne
    @JoinColumn(name = "BUILDING_BUNDLE_ID")
    private BuildingBundle buildingBundle;
    @Column(name = "SLOT_NUMBER")
    private int slotNumber;

    public AvatarBuildingBundle() {
    }

    public AvatarBuildingBundle(int id, Avatar avatar, BuildingBundle buildingBundle, int slotNumber) {
        this.id = id;
        this.avatar = avatar;
        this.buildingBundle = buildingBundle;
        this.slotNumber = slotNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public BuildingBundle getBuildingBundle() {
        return buildingBundle;
    }

    public void setBuildingBundle(BuildingBundle buildingBundle) {
        this.buildingBundle = buildingBundle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return "AvatarBuildingBundle{" +
                "id=" + id +
                ", avatar=" + avatar +
                ", buildingBundle=" + buildingBundle +
                ", slotNumber=" + slotNumber +
                '}';
    }
}
