package com.nebula.nebula_resource.app.dto.skyisland;

public class SkyIslandCoordinateDTO {
    private double pc1;
    private double pc2;
    private double pc3;
    private String keyword1;
    private String keyword2;

    public SkyIslandCoordinateDTO() {
    }

    public SkyIslandCoordinateDTO(double pc1, double pc2, double pc3, String keyword1, String keyword2) {
        this.pc1 = pc1;
        this.pc2 = pc2;
        this.pc3 = pc3;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
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
        return "SkyIslandCoordinateDTO{" +
                "pc1=" + pc1 +
                ", pc2=" + pc2 +
                ", pc3=" + pc3 +
                ", keyword1='" + keyword1 + '\'' +
                ", keyword2='" + keyword2 + '\'' +
                '}';
    }
}
