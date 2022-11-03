package com.nebula.nebula_resource.app.dto.avatar;

import java.util.List;
import java.util.Map;

public class AvatarDTO {
    private String settingsName;
    private Map<String, Integer> selectedElements;
    private List<BlendShapeDTO> blendshapes;
    private int MinLod;
    private int MaxLod;
    private List<Integer> SkinColor;
    private List<Integer> EyeColor;
    private List<Integer> HairColor;
    private List<Integer> UnderpantsColor;
    private List<Integer> TeethColor;
    private List<Integer> OralCavityColor;
    private int Height;
    private int HeadSize;

    public AvatarDTO() {
    }

    public AvatarDTO(String settingsName, Map<String, Integer> selectedElements, List<BlendShapeDTO> blendshapes, int minLod, int maxLod, List<Integer> skinColor, List<Integer> eyeColor, List<Integer> hairColor, List<Integer> underpantsColor, List<Integer> teethColor, List<Integer> oralCavityColor, int height, int headSize) {
        this.settingsName = settingsName;
        this.selectedElements = selectedElements;
        this.blendshapes = blendshapes;
        MinLod = minLod;
        MaxLod = maxLod;
        SkinColor = skinColor;
        EyeColor = eyeColor;
        HairColor = hairColor;
        UnderpantsColor = underpantsColor;
        TeethColor = teethColor;
        OralCavityColor = oralCavityColor;
        Height = height;
        HeadSize = headSize;
    }

    public String getSettingsName() {
        return settingsName;
    }

    public void setSettingsName(String settingsName) {
        this.settingsName = settingsName;
    }

    public Map<String, Integer> getSelectedElements() {
        return selectedElements;
    }

    public void setSelectedElements(Map<String, Integer> selectedElements) {
        this.selectedElements = selectedElements;
    }

    public List<BlendShapeDTO> getBlendshapes() {
        return blendshapes;
    }

    public void setBlendshapes(List<BlendShapeDTO> blendshapes) {
        this.blendshapes = blendshapes;
    }

    public int getMinLod() {
        return MinLod;
    }

    public void setMinLod(int minLod) {
        MinLod = minLod;
    }

    public int getMaxLod() {
        return MaxLod;
    }

    public void setMaxLod(int maxLod) {
        MaxLod = maxLod;
    }

    public List<Integer> getSkinColor() {
        return SkinColor;
    }

    public void setSkinColor(List<Integer> skinColor) {
        SkinColor = skinColor;
    }

    public List<Integer> getEyeColor() {
        return EyeColor;
    }

    public void setEyeColor(List<Integer> eyeColor) {
        EyeColor = eyeColor;
    }

    public List<Integer> getHairColor() {
        return HairColor;
    }

    public void setHairColor(List<Integer> hairColor) {
        HairColor = hairColor;
    }

    public List<Integer> getUnderpantsColor() {
        return UnderpantsColor;
    }

    public void setUnderpantsColor(List<Integer> underpantsColor) {
        UnderpantsColor = underpantsColor;
    }

    public List<Integer> getTeethColor() {
        return TeethColor;
    }

    public void setTeethColor(List<Integer> teethColor) {
        TeethColor = teethColor;
    }

    public List<Integer> getOralCavityColor() {
        return OralCavityColor;
    }

    public void setOralCavityColor(List<Integer> oralCavityColor) {
        OralCavityColor = oralCavityColor;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getHeadSize() {
        return HeadSize;
    }

    public void setHeadSize(int headSize) {
        HeadSize = headSize;
    }

    @Override
    public String toString() {
        return "AvatarDTO{" +
                "settingsName='" + settingsName + '\'' +
                ", selectedElements=" + selectedElements +
                ", blendshapes=" + blendshapes +
                ", MinLod=" + MinLod +
                ", MaxLod=" + MaxLod +
                ", SkinColor=" + SkinColor +
                ", EyeColor=" + EyeColor +
                ", HairColor=" + HairColor +
                ", UnderpantsColor=" + UnderpantsColor +
                ", TeethColor=" + TeethColor +
                ", OralCavityColor=" + OralCavityColor +
                ", Height=" + Height +
                ", HeadSize=" + HeadSize +
                '}';
    }
}
