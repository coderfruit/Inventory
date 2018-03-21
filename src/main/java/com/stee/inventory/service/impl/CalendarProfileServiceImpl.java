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
 * File Name    : CalendarProfileServiceImpl
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
import com.stee.inventory.dao.CalendarProfileRepository;
import com.stee.inventory.service.ICalendarProfileService;
import com.stee.sel.dc.CalendarProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SerryMiano on 2017/1/10.
 */
@Service
public class CalendarProfileServiceImpl implements ICalendarProfileService {

    @Autowired
    private CalendarProfileRepository repository;

    @Override
    public List<String> getCalendarProfileIds() {
        List<String> list = Lists.newArrayList();
        List<CalendarProfileEntity> profiles = repository.findAll();
        profiles.forEach(profile -> {
            list.add(profile.getName());
        });
        return list;
    }
}
