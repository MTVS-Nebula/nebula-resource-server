package com.nebula.nebula_resource.app.dao.entity.item;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PLACE_OBJECT", schema = "item")
@SequenceGenerator(
        name = "SEQ_PLACE_OBJECT_ID_GENERATOR",
        sequenceName = "SEQ_PLACE_OBJECT_ID",
        initialValue = 1,
        allocationSize = 1
)
public class PlaceObject {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PLACE_OBJECT_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @Column(name = "SO_NAME")
    private String soName;

    public PlaceObject() {
    }

    public PlaceObject(int id, String soName) {
        this.id = id;
        this.soName = soName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoName() {
        return soName;
    }

    public void setSoName(String soName) {
        this.soName = soName;
    }

    @Override
    public String toString() {
        return "PlaceObject{" +
                "id=" + id +
                ", soName='" + soName + '\'' +
                '}';
    }
}
