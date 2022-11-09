package com.nebula.nebula_resource.app.dao.entity.item.clothes;

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
@Table(name = "TBL_CL_BUFF", schema = "item")
@SequenceGenerator(
        name = "SEQ_CL_BUFF_ID_GENERATOR",
        sequenceName = "SEQ_CL_BUFF_ID",
        initialValue = 1,
        allocationSize = 1
)
public class ClothesBuff {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CL_BUFF_ID_GENERATOR"
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
    @JoinColumn(name = "CLOTHES_ID")
    private BaseClothes clothes;

    public ClothesBuff() {
    }

    public ClothesBuff(int id, int buffStat, int buffValue, int buffMin, int buffMax, BaseClothes clothes) {
        this.id = id;
        this.buffStat = buffStat;
        this.buffValue = buffValue;
        this.buffMin = buffMin;
        this.buffMax = buffMax;
        this.clothes = clothes;
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

    public BaseClothes getClothes() {
        return clothes;
    }

    public void setClothes(BaseClothes clothes) {
        this.clothes = clothes;
    }

    @Override
    public String toString() {
        return "ClothesBuff{" +
                "id=" + id +
                ", buffStat=" + buffStat +
                ", buffValue=" + buffValue +
                ", buffMin=" + buffMin +
                ", buffMax=" + buffMax +
                ", clothes=" + clothes +
                '}';
    }
}
