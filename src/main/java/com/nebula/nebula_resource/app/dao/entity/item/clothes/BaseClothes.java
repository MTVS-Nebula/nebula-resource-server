package com.nebula.nebula_resource.app.dao.entity.item.clothes;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BASE_CLOTHES", schema = "item")
@SequenceGenerator(
        name = "SEQ_BASE_CLOTHES_ID_GENERATOR",
        sequenceName = "SEQ_BASE_CLOTHES_ID",
        initialValue = 1,
        allocationSize = 1
)
public class BaseClothes {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_BASE_CLOTHES_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ELEMENT_ID")
    private Integer elementId;

    public BaseClothes() {
    }

    public BaseClothes(int id, String name, Integer elementId) {
        this.id = id;
        this.name = name;
        this.elementId = elementId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", elementId=" + elementId +
                '}';
    }
}
