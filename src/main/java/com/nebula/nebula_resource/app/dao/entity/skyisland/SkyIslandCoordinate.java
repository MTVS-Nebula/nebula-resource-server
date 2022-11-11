package com.nebula.nebula_resource.app.dao.entity.skyisland;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_SKY_ISLAND_COORDINATE", schema = "skyisland")
public class SkyIslandCoordinate {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "ID")
    private SkyIsland skyIsland;
    @Column(name = "PC1", nullable = false)
    private double pc1;
    @Column(name = "PC2",nullable = false)
    private double pc2;
    @Column(name = "PC3",nullable = false)
    private double pc3;
    @Column(name = "KEYWORD1", nullable = false)
    private String keyword1;
    @Column(name = "KEYWORD2", nullable = false)
    private String keyword2;

    public SkyIslandCoordinate() {
    }

    public SkyIslandCoordinate(SkyIsland skyIsland, double pc1, double pc2, double pc3, String keword1,
                               String keyword2) {
        this.skyIsland = skyIsland;
        this.pc1 = pc1;
        this.pc2 = pc2;
        this.pc3 = pc3;
        this.keyword1 = keword1;
        this.keyword2 = keyword2;
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

    public double getPc1() {
        return pc1;
    }

    public void setPc1(double pc1) {
        this.pc1 = pc1;
    }

    public double getPc2() {
        return pc2;
    }

    public void setPc2(double pc2) {
        this.pc2 = pc2;
    }

    public double getPc3() {
        return pc3;
    }

    public void setPc3(double pc3) {
        this.pc3 = pc3;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    @Override
    public String toString() {
        return "SkyIslandCoordinate{" +
                "id=" + id +
                ", skyIsland=" + skyIsland +
                ", pc1=" + pc1 +
                ", pc2=" + pc2 +
                ", pc3=" + pc3 +
                ", keword1='" + keyword1 + '\'' +
                ", keyword2='" + keyword2 + '\'' +
                '}';
    }
}
