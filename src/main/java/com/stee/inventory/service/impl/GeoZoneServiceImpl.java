/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : lim
 * File Name    : GeoZoneServiceImpl
 * Author       : Jerry
 * Created      : 2017/1/10
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
package com.stee.inventory.service.impl;

import com.google.common.collect.Lists;
import com.stee.inventory.dao.GeoZoneRepository;
import com.stee.inventory.service.IGeoZoneService;
import com.stee.sel.gis.GeoZoneEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SerryMiano on 2017/1/10.
 */
@Service
public class GeoZoneServiceImpl implements IGeoZoneService {
    @Autowired
    GeoZoneRepository repository;

    @Override
    public List<GeoZoneEntity> getGeoZoneIds() {
        List<String> list = Lists.newArrayList();
        List<GeoZoneEntity> zones = repository.findAll();
//        zones.forEach(zone -> {
//            list.add(zone.getName());
//        });
        return zones;
    }

    @Override
    public GeoZoneEntity findGZoneByName(Integer name) {
        GeoZoneEntity gZone = null;
        if (null != name && !name.equals("")) {
            gZone = repository.findByGeozoneId(name);
        }
        return gZone;
    }
}
