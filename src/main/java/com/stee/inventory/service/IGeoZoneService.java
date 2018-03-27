package com.stee.inventory.service;

import com.stee.sel.gis.GeoZoneEntity;

import java.util.List;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface IGeoZoneService {
    List<GeoZoneEntity> getGeoZoneIds();

    GeoZoneEntity findGZoneByName(Integer name);

}
