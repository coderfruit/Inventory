package com.stee.inventory.controller;

import com.stee.inventory.entity.Result;
import com.stee.inventory.service.IAlarmThresholdService;
import com.stee.sel.inventory.AlarmThreshold;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_ASM
 * File Name    : PoleModelController.java
 * Author       : Jerry
 * Created      : 2016年11月15日 下午1:39:24
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@RestController
@RequestMapping(value = "/threshold")
@ComponentScan(basePackages = {"com.stee.sel"})
@EntityScan({"com.stee.sel.inventory","com.stee.sel.report","com.stee.sel.dc","com.stee.sel.gis","com.stee.sel.constant",
        "com.stee.sel.secutiry","com.stee.sel.common","com.stee.sel.field","com.stee.sel.inf","com.stee.sel.secutiry","com.stee.sel.alarm"})
public class AlarmThresholdController {
    @Resource
    private IAlarmThresholdService service;

    @RequestMapping(path = "/save",method = RequestMethod.POST)
    public Result<AlarmThreshold> save(@RequestBody AlarmThreshold alarmThreshold){
        return service.save(alarmThreshold);
    }

    @RequestMapping(path = "/delete",method = RequestMethod.DELETE)
    public Result deleteById(Integer id){
        return service.deleteById(id);
    }

    @RequestMapping(path = "/update",method = RequestMethod.PUT)
    public Result<AlarmThreshold> update(@RequestBody AlarmThreshold alarmThreshold){
        return service.save(alarmThreshold);
    }

    @RequestMapping(path = "/get",method = RequestMethod.GET)
    public Result<AlarmThreshold> findAlarmThresholdById(Integer id){
        return service.findAlarmThresholdById(id);
    }
}
