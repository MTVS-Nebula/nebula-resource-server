package com.nebula.nebula_resource.app.dao.repository.item;

import com.nebula.nebula_resource.app.dao.entity.item.PlaceObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceObjectRepository extends JpaRepository<PlaceObject, Integer> {
    PlaceObject findBySoName(String soName);
}
