package com.nebula.nebula_resource.app.dao.entity.item.clothes;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CLOTHES", schema = "item")
@SequenceGenerator(
        name = "SEQ_CLOTHES_ID_GENERATOR",
        sequenceName = "SEQ_CLOTHES_ID",
        initialValue = 1,
        allocationSize = 1
)
public class Clothes {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CLOTHES_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "BASE_CLOTHES_ID")
    private BaseClothes baseClothes;

    @OneToMany(mappedBy = "clothes")
    private List<ClothesBuff> buffs;
    @ManyToOne
    @JoinColumn
    private Avatar creator;

    public Clothes() {
    }

    public Clothes(int id, BaseClothes baseClothes, List<ClothesBuff> buffs, Avatar creator) {
        this.id = id;
        this.baseClothes = baseClothes;
        this.buffs = buffs;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BaseClothes getBaseClothes() {
        return baseClothes;
    }

    public void setBaseClothes(BaseClothes baseClothes) {
        this.baseClothes = baseClothes;
    }

    public List<ClothesBuff> getBuffs() {
        return buffs;
    }

    public void setBuffs(List<ClothesBuff> buffs) {
        this.buffs = buffs;
    }

    public Avatar getCreator() {
        return creator;
    }

    public void setCreator(Avatar creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "id=" + id +
                ", baseClothes=" + baseClothes +
                ", buffs=" + buffs +
                ", creator=" + creator +
                '}';
    }
}
