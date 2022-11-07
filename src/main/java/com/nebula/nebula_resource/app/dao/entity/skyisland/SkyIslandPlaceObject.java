package com.nebula.nebula_resource.app.dao.entity.skyisland;

import com.nebula.nebula_resource.app.dao.entity.item.PlaceObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "TBL_SI_PO", schema = "skyisland")
@SequenceGenerator(
        name = "SEQ_SI_PO_ID_GENERATOR",
        sequenceName = "SEQ_SI_PO_ID",
        initialValue = 1,
        allocationSize = 1
)
public class SkyIslandPlaceObject {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SI_PO_ID_GENERATOR"
    )
    @Column(name = "ID")
    int id;
    @ManyToOne
    @JoinColumn(name = "SKY_ISLAND_ID")
    private SkyIsland skyIsland;
    @ManyToOne
    @JoinColumn(name = "PLACE_OBJECT_ID")
    private PlaceObject placeObject;

    @Column(name = "GRID_NUMBER")
    private int gridNumber;
    @Column(name = "ORDER_X")
    private int x;
    @Column(name = "ORDER_Y")
    private int y;
    @Column(name = "DIRECTION")
    private int dir;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "DELETED_DATE")
    private Date deletedDate;

    public SkyIslandPlaceObject() {
    }

    public SkyIslandPlaceObject(int id, SkyIsland skyIsland, PlaceObject placeObject, int gridNumber, int x, int y,
                                int dir,
                                Date createdDate, Date deletedDate) {
        this.id = id;
        this.skyIsland = skyIsland;
        this.placeObject = placeObject;
        this.gridNumber = gridNumber;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.createdDate = createdDate;
        this.deletedDate = deletedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SkyIsland getSkyIsland() {
        return skyIsland;
    }

    public void setSkyIsland(SkyIsland skyIsland) {
        this.skyIsland = skyIsland;
    }

    public PlaceObject getPlaceObject() {
        return placeObject;
    }

    public void setPlaceObject(PlaceObject placeObject) {
        this.placeObject = placeObject;
    }

    public int getGridNumber() {
        return gridNumber;
    }

    public void setGridNumber(int gridNumber) {
        this.gridNumber = gridNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public String toString() {
        return "SkyIslandPlaceObject{" +
                "id=" + id +
                ", skyIsland=" + skyIsland +
                ", placeObject=" + placeObject +
                ", gridNumber=" + gridNumber +
                ", x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", createdDate=" + createdDate +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
