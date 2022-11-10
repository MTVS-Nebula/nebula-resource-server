package com.nebula.nebula_resource.app.dto.avatar;

import org.springframework.web.multipart.MultipartFile;

public class AvatarCreateDTO {
    private String AvatarName;
    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
    private MultipartFile image;

    public AvatarCreateDTO() {
    }

    public AvatarCreateDTO(String avatarName, String tag1, String tag2, String tag3, String tag4, MultipartFile image) {
        AvatarName = avatarName;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.image = image;
    }

    public String getAvatarName() {
        return AvatarName;
    }

    public void setAvatarName(String avatarName) {
        AvatarName = avatarName;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "AvatarCreateDTO{" +
                "AvatarName='" + AvatarName + '\'' +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                ", tag3='" + tag3 + '\'' +
                ", tag4='" + tag4 + '\'' +
                ", image=" + image +
                '}';
    }
}
