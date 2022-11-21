package com.nebula.nebula_resource.app.dao.repository.item;

import com.nebula.nebula_resource.app.dao.entity.item.clothes.BaseClothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseClothesRepository extends JpaRepository<BaseClothes, Integer> {
    int countBy();
    BaseClothes findById(int id);
}
