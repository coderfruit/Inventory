package com.stee.inventory.repository;

import com.stee.sel.gzm.GZone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface GeoZoneRepository extends JpaRepository<GZone, String> {
    GZone findByName(String name);
}
