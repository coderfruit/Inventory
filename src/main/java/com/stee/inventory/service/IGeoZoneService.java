package com.stee.inventory.service;

import java.util.List;

import com.stee.inventory.entity.sel.GeoZoneEntity;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface IGeoZoneService {
    List<String> getGeoZoneIds();

    GeoZoneEntity findGZoneByName(Integer name);

}
