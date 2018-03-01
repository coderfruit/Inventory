package com.stee.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stee.inventory.entity.sel.GeoZoneEntity;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface GeoZoneRepository extends JpaRepository<GeoZoneEntity, String> {
    GeoZoneEntity findByName(Integer name);
}
