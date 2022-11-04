package com.nebula.nebula_resource.app.dao.entity.skyisland;

import com.nebula.nebula_resource.app.dao.entity.skyisland.embeddable.SkyIslandPlaceObjectId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "TBL_SI_PO", schema = "skyisland")
public class SkyIslandPlaceObject {
    @EmbeddedId
    private SkyIslandPlaceObjectId id;
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

    public SkyIslandPlaceObject(SkyIslandPlaceObjectId id, int gridNumber, int x, int y, int dir, Date createdDate, Date deletedDate) {
        this.id = id;
        this.gridNumber = gridNumber;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.createdDate = createdDate;
        this.deletedDate = deletedDate;
    }

    public SkyIslandPlaceObjectId getId() {
        return id;
    }

    public void setId(SkyIslandPlaceObjectId id) {
        this.id = id;
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
                ", gridNumber=" + gridNumber +
                ", x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", createdDate=" + createdDate +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
