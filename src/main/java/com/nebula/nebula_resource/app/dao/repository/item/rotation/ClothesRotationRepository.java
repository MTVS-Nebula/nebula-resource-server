package com.nebula.nebula_resource.app.dao.repository.item.rotation;

import com.nebula.nebula_resource.app.dao.entity.item.rotation.ClothesRotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothesRotationRepository extends JpaRepository<ClothesRotation, Integer> {
    List<ClothesRotation> findBy();
}
