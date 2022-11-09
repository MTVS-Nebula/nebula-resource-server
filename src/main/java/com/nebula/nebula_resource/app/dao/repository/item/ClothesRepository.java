package com.nebula.nebula_resource.app.dao.repository.item;

import com.nebula.nebula_resource.app.dao.entity.item.clothes.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {
    Clothes findById(int id);
}
