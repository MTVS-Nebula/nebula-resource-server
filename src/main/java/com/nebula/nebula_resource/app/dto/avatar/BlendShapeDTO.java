package com.nebula.nebula_resource.app.dto.avatar;

public class BlendShapeDTO {
    private String blendshapeName;
    private int type;
    private int group;
    private int value;

    public BlendShapeDTO() {
    }

    public BlendShapeDTO(String blendshapeName, int type, int group, int value) {
        this.blendshapeName = blendshapeName;
        this.type = type;
        this.group = group;
        this.value = value;
    }

    public String getBlendshapeName() {
        return blendshapeName;
    }

    public void setBlendshapeName(String blendshapeName) {
        this.blendshapeName = blendshapeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BlendShapeDTO{" +
                "blendshapeName='" + blendshapeName + '\'' +
                ", type=" + type +
                ", group=" + group +
                ", value=" + value +
                '}';
    }
}
