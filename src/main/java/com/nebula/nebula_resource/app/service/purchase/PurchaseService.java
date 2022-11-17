package com.nebula.nebula_resource.app.service.purchase;

import com.nebula.nebula_resource.app.dto.purchase.PurchaseBuildingBundleDTO;

public interface PurchaseService {
    PurchaseBuildingBundleDTO purchaseBundle(String avatarName, String bundleName);
}
