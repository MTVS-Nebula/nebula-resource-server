package com.nebula.nebula_resource.app.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_AVATAR_BS", schema = "avatar")
@SequenceGenerator(
        name = "SEQ_AVATAR_BS_ID_GENERATOR",
        sequenceName = "SEQ_AVATAR_BS_ID",
        initialValue = 1,
        allocationSize = 1
)
public class AvatarBlendShape {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AVATAR_BS_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "AVATAR_TEXTURE_ID")
    private AvatarTexture avatarTexture;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private int type;
    @Column(name = "GROUP")
    private int group;
    @Column(name = "VALUE")
    private double value;

    public AvatarBlendShape() {
    }

    public AvatarBlendShape(int id, AvatarTexture avatarTexture, String name, int type, int group, double value) {
        this.id = id;
        this.avatarTexture = avatarTexture;
        this.name = name;
        this.type = type;
        this.group = group;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AvatarTexture getAvatarTexture() {
        return avatarTexture;
    }

    public void setAvatarTexture(AvatarTexture avatarTexture) {
        this.avatarTexture = avatarTexture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AvatarBlendShape{" +
                "id=" + id +
                ", avatarTexture=" + avatarTexture +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", group=" + group +
                ", value=" + value +
                '}';
    }
}
