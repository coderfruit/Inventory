package com.stee.inventory.dao;

import com.stee.sel.gis.GeoZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface GeoZoneRepository extends JpaRepository<GeoZoneEntity, Integer> {
    GeoZoneEntity findByGeozoneId(Integer name);
}
