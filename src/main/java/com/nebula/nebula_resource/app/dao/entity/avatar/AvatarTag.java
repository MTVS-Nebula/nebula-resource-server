package com.nebula.nebula_resource.app.dao.entity.avatar;

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
@Table(name = "TBL_AVATAR_TAG", schema = "avatar")
@SequenceGenerator(
        name = "SEQ_AVATAR_TAG_ID_GENERATOR",
        sequenceName = "SEQ_AVATAR_TAG_ID",
        initialValue = 1,
        allocationSize = 1
)
public class AvatarTag {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AVATAR_TAG_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @Column(name = "NUMBER")
    private int num;
    @Column(name = "CONTENT")
    private String tag;
    @ManyToOne
    @JoinColumn(name = "AVATAR_ID")
    private Avatar avatar;

    public AvatarTag() {
    }

    public AvatarTag(int id, int num, String tag, Avatar avatar) {
        this.id = id;
        this.num = num;
        this.tag = tag;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AvatarTag{" +
                "id=" + id +
                ", num=" + num +
                ", tag='" + tag + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
