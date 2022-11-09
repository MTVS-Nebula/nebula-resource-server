package com.nebula.nebula_resource.app.dao.entity.inventory;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.BaseClothes;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.Clothes;
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
@Table(name = "TBL_AVATAR_CL", schema = "avatar")
@SequenceGenerator(
        name = "SEQ_AVATAR_CL_ID_GENERATOR",
        sequenceName = "SEQ_AVATAR_CL_ID",
        initialValue = 1,
        allocationSize = 1
)
public class AvatarClothes {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AVATAR_CL_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "AVATAR_ID")
    private Avatar avatar;
    @ManyToOne
    @JoinColumn(name = "CLOTHES_ID", unique = true)
    private Clothes clothes;
    @Column(name = "SLOT_NUMBER")
    private int slotNumber;

    public AvatarClothes() {
    }

    public AvatarClothes(int id, Avatar avatar, Clothes clothes, int slotNumber) {
        this.id = id;
        this.avatar = avatar;
        this.clothes = clothes;
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

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return "AvatarClothes{" +
                "id=" + id +
                ", avatar=" + avatar +
                ", clothes=" + clothes +
                ", slotNumber=" + slotNumber +
                '}';
    }
}
