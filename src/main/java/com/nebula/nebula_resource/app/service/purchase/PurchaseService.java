package com.nebula.nebula_resource.app.service.purchase;

import com.nebula.nebula_resource.app.dto.purchase.PurchaseBuildingBundleDTO;
import com.nebula.nebula_resource.app.dto.purchase.PurchaseListDTO;
import org.springframework.transaction.annotation.Transactional;

public interface PurchaseService {
    PurchaseListDTO getPurchaseList(String avatarName);
    PurchaseBuildingBundleDTO purchaseBundle(String avatarName, String bundleName);
}
