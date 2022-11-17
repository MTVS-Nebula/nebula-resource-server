package com.nebula.nebula_resource.app.dto.purchase;

public class BuildingBundleNameDTO {
    private String buildingBundleName;

    public BuildingBundleNameDTO() {
    }

    public BuildingBundleNameDTO(String buildingBundleName) {
        this.buildingBundleName = buildingBundleName;
    }

    public String getBuildingBundleName() {
        return buildingBundleName;
    }

    public void setBuildingBundleName(String buildingBundleName) {
        this.buildingBundleName = buildingBundleName;
    }

    @Override
    public String toString() {
        return "BuildingBundleNameDTO{" +
                "buildingBundleName='" + buildingBundleName + '\'' +
                '}';
    }
}
