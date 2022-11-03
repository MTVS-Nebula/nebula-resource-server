package com.nebula.nebula_resource.app.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_AVATAR_CS", schema = "avatar")
@SequenceGenerator(
        name = "SEQ_AVATAR_CS_ID_GENERATOR",
        sequenceName = "SEQ_AVATAR_CS_ID",
        initialValue = 1,
        allocationSize = 1
)
public class AvatarColorSet {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AVATAR_CS_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "AVATAR_TEXTURE_ID")
    private AvatarTexture avatarTexture;
    @Column(name = "RED")
    private double red;
    @Column(name = "GREEN")
    private double green;
    @Column(name = "BLUE")
    private double blue;
    @Column(name = "ALPHA")
    private double alpha;

    public AvatarColorSet() {
    }

    public AvatarColorSet(int id, AvatarTexture avatarTexture, double red, double green, double blue, double alpha) {
        this.id = id;
        this.avatarTexture = avatarTexture;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
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

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public String toString() {
        return "AvatarColorSet{" +
                "id=" + id +
                ", avatarTexture=" + avatarTexture +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", alpha=" + alpha +
                '}';
    }
}
