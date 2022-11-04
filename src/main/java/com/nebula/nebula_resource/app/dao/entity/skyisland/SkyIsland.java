package com.nebula.nebula_resource.app.dao.entity.skyisland;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_SKY_ISLAND", schema = "skyisland")
@SequenceGenerator(
        name = "SEQ_SKY_ISLAND_ID_GENERATOR",
        sequenceName = "SEQ_SKY_ISLAND_ID",
        initialValue = 1,
        allocationSize = 1
)
public class SkyIsland {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SKY_ISLAND_ID_GENERATOR"
    )
    @Column(name = "ID")
    int id;
    @OneToOne
    @JoinColumn(name = "AVATAR_ID")
    private Avatar avatar;
    @OneToMany(mappedBy = "id.skyIsland") // 오류는 나지만 동작은 잘함.
    private List<SkyIslandPlaceObject> placeObjects;


    public SkyIsland() {
    }

    public SkyIsland(int id, Avatar avatar, List<SkyIslandPlaceObject> placeObjects) {
        this.id = id;
        this.avatar = avatar;
        this.placeObjects = placeObjects;
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

    public List<SkyIslandPlaceObject> getPlaceObjects() {
        return placeObjects;
    }

    public void setPlaceObjects(List<SkyIslandPlaceObject> placeObjects) {
        this.placeObjects = placeObjects;
    }

    @Override
    public String toString() {
        return "SkyIsland{" +
                "id=" + id +
                ", avatar=" + avatar +
                ", placeObjects=" + placeObjects +
                '}';
    }
}
