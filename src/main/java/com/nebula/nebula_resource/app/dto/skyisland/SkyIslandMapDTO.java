package com.nebula.nebula_resource.app.dto.skyisland;

public class SkyIslandMapDTO {
    private int skyIslandId;
    private String avatarName;
    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
    private String imageUrl;

    public SkyIslandMapDTO() {
    }

    public SkyIslandMapDTO(int skyIslandId, String avatarName, String tag1, String tag2, String tag3, String tag4,
                           String imageUrl) {
        this.skyIslandId = skyIslandId;
        this.avatarName = avatarName;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.imageUrl = imageUrl;
    }

    public int getSkyIslandId() {
        return skyIslandId;
    }

    public void setSkyIslandId(int skyIslandId) {
        this.skyIslandId = skyIslandId;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "SkyIslandMapDTO{" +
                "skyIslandId=" + skyIslandId +
                ", avatarName='" + avatarName + '\'' +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                ", tag3='" + tag3 + '\'' +
                ", tag4='" + tag4 + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
