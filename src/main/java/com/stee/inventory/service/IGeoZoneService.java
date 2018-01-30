package com.stee.inventory.service;

import com.stee.sel.gzm.GZone;

import java.util.List;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface IGeoZoneService {
    List<String> getGeoZoneIds();

    GZone findGZoneByName(String name);

}
