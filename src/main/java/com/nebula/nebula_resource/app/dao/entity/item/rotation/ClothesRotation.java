package com.nebula.nebula_resource.app.dao.entity.item.rotation;

import com.nebula.nebula_resource.app.dao.entity.item.clothes.BaseClothes;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CL_ROTATION", schema = "rotation")
@SequenceGenerator(
        name = "SEQ_CL_ROTATION_ID_GENERATOR",
        sequenceName = "SEQ_CL_ROTATION_ID",
        initialValue = 1,
        allocationSize = 1
)
public class ClothesRotation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CL_ROTATION_ID"
    )
    @Column(name = "ID")
    private int id;
    @OneToOne
    @JoinColumn(name = "CL_ID")
    private BaseClothes clothes;

    public ClothesRotation() {
    }

    public ClothesRotation(int id, BaseClothes clothes) {
        this.id = id;
        this.clothes = clothes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BaseClothes getClothes() {
        return clothes;
    }

    public void setClothes(BaseClothes clothes) {
        this.clothes = clothes;
    }
}
