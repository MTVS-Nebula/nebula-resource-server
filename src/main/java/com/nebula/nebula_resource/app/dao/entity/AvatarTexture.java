package com.nebula.nebula_resource.app.dao.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_AVATAR_TEXTURE", schema = "avatar")
@SequenceGenerator(
        name = "SEQ_AVATAR_TEXTURE_ID_GENERATOR",
        sequenceName = "SEQ_AVATAR_TEXTURE_ID",
        initialValue = 1,
        allocationSize = 1
)
public class AvatarTexture {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AVATAR_TEXTURE_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @OneToOne()
    @JoinColumn(name = "AVATAR_ID")
    private Avatar avatar;
    @Column(name = "SETTINGS_NAME")
    private String settingsName;
    @OneToMany(mappedBy = "avatarTexture")
    private List<AvatarBlendShape> blendshapes;
    @Column(name = "MIN_LOD")
    private int minLod;
    @Column(name = "MAX_LOD")
    private int maxLod;
    @Column(name = "HEIGHT")
    private double height;
    @Column(name = "HEAD_SIZE")
    private double headSize;
    @OneToMany(mappedBy = "avatarTexture")
    private List<AvatarColorSet> skinColor;

    public AvatarTexture() {
    }

    public AvatarTexture(int id, Avatar avatar, String settingsName, List<AvatarBlendShape> blendshapes, int minLod, int maxLod, double height, double headSize, List<AvatarColorSet> skinColor) {
        this.id = id;
        this.avatar = avatar;
        this.settingsName = settingsName;
        this.blendshapes = blendshapes;
        this.minLod = minLod;
        this.maxLod = maxLod;
        this.height = height;
        this.headSize = headSize;
        this.skinColor = skinColor;
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

    public String getSettingsName() {
        return settingsName;
    }

    public void setSettingsName(String settingsName) {
        this.settingsName = settingsName;
    }

    public List<AvatarBlendShape> getBlendshapes() {
        return blendshapes;
    }

    public void setBlendshapes(List<AvatarBlendShape> blendshapes) {
        this.blendshapes = blendshapes;
    }

    public int getMinLod() {
        return minLod;
    }

    public void setMinLod(int minLod) {
        this.minLod = minLod;
    }

    public int getMaxLod() {
        return maxLod;
    }

    public void setMaxLod(int maxLod) {
        this.maxLod = maxLod;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeadSize() {
        return headSize;
    }

    public void setHeadSize(double headSize) {
        this.headSize = headSize;
    }

    public List<AvatarColorSet> getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(List<AvatarColorSet> skinColor) {
        this.skinColor = skinColor;
    }

    @Override
    public String toString() {
        return "AvatarTexture{" +
                "id=" + id +
                ", avatar=" + avatar +
                ", settingsName='" + settingsName + '\'' +
                ", blendshapes=" + blendshapes +
                ", minLod=" + minLod +
                ", maxLod=" + maxLod +
                ", height=" + height +
                ", headSize=" + headSize +
                ", skinColor=" + skinColor +
                '}';
    }
}
