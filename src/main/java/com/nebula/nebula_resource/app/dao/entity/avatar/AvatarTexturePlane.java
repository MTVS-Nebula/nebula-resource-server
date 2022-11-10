package com.nebula.nebula_resource.app.dao.entity.avatar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_AVATAR_TT_PLANE", schema = "avatar")
@SequenceGenerator(
        name = "SEQ_AVATAR_TT_PLANE_ID_GENERATOR",
        sequenceName = "SEQ_AVATAR_TT_PLANE_ID",
        initialValue = 1,
        allocationSize = 1
)
public class AvatarTexturePlane {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AVATAR_TT_PLANE_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @Column(name = "TT_PLANE")
    private String texturePlane;
    @OneToOne
    @JoinColumn(name = "AVATAR_ID")
    private Avatar avatar;

    public AvatarTexturePlane() {
    }

    public AvatarTexturePlane(int id, String texturePlane, Avatar avatar) {
        this.id = id;
        this.texturePlane = texturePlane;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexturePlane() {
        return texturePlane;
    }

    public void setTexturePlane(String texturePlane) {
        this.texturePlane = texturePlane;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AvatarTexturePlane{" +
                "id=" + id +
                ", texturePlane='" + texturePlane + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
