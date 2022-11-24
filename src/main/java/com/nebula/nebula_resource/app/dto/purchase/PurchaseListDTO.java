package com.nebula.nebula_resource.app.dto.purchase;

import java.util.List;

public class PurchaseListDTO {
    private List<PurchaseInfoDTO> clothesList;
    private List<PurchaseInfoDTO> bundleList;

    public PurchaseListDTO() {
    }

    public PurchaseListDTO(List<PurchaseInfoDTO> clothesList, List<PurchaseInfoDTO> bundleList) {
        this.clothesList = clothesList;
        this.bundleList = bundleList;
    }

    public List<PurchaseInfoDTO> getClothesList() {
        return clothesList;
    }

    public void setClothesList(List<PurchaseInfoDTO> clothesList) {
        this.clothesList = clothesList;
    }

    public List<PurchaseInfoDTO> getBundleList() {
        return bundleList;
    }

    public void setBundleList(List<PurchaseInfoDTO> bundleList) {
        this.bundleList = bundleList;
    }

    @Override
    public String toString() {
        return "PurchaseListDTO{" +
                "clothesList=" + clothesList +
                ", bundleList=" + bundleList +
                '}';
    }
}
