package com.nebula.nebula_resource.app.dto.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.avatar.AvatarTag;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AvatarDTO {
    private int id;
    private String name;
    private String imageUrl;
    private List<String> hashTags;
    private int skyIslandId;
    private Map<String, Object> texture;

    public AvatarDTO() {
    }

    public AvatarDTO(int id, String name, String imageUrl, List<String> hashTags, int skyIslandId,
                     Map<String , Object> texture) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.hashTags = hashTags;
        this.skyIslandId = skyIslandId;
        this.texture = texture;
    }

    public AvatarDTO(Avatar avatar){
        this.id = avatar.getId();
        this.name = avatar.getAvatarName();
        if(avatar.getImage() != null){
            this.imageUrl = avatar.getImage().getSavedPath();
        }
        this.hashTags = new ArrayList<>();
        if (avatar.getAvatarTags() != null){
            for (AvatarTag avatarTag : avatar.getAvatarTags()){
                hashTags.add(avatarTag.getTag());
            }
        }
        if(avatar.getAvatarTexture() != null){
            JSONParser jsonParser = new JSONParser();
            try {
                Object obj = jsonParser.parse(avatar.getAvatarTexture().getTexturePlane());
                this.texture = (Map<String, Object>) obj;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<String> hashTags) {
        this.hashTags = hashTags;
    }

    public int getSkyIslandId() {
        return skyIslandId;
    }

    public void setSkyIslandId(int skyIslandId) {
        this.skyIslandId = skyIslandId;
    }

    public Map<String, Object> getTexture() {
        return texture;
    }

    public void setTexture(Map<String, Object> texture) {
        this.texture = texture;
    }

    @Override
    public String toString() {
        return "AvatarDTO{" +
                "id=" + id +
                ", name=" + name +
                ", imageUrl='" + imageUrl + '\'' +
                ", hashTags=" + hashTags +
                ", skyIslandId=" + skyIslandId +
                ", texture=" + texture +
                '}';
    }
}
