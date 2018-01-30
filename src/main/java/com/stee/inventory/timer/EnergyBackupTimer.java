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
 * File Name    : EnergyBackupTimer
 * Author       : Jerry
 * Created      : 2017/1/17
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
package com.stee.inventory.timer;

import com.stee.inventory.repository.EnergyUsageHistRepository;
import com.stee.inventory.repository.LampInfoRepository;
import com.stee.sel.lfm.EnergyHist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by SerryMiano on 2017/1/17.
 */
@Component
public class EnergyBackupTimer {

    @Autowired
    LampInfoRepository repository;

    @Autowired
    EnergyUsageHistRepository histRepository;

//    @Scheduled(cron = "0 0 9 * * ?")
    public void execute() {
        repository.findAll().forEach(lamp -> {
            EnergyHist energyHist = new EnergyHist();
            energyHist.setLuminaireId(lamp.getId());
            energyHist.setEnergyUsage(lamp.getLampStatus().getEnergyUsage().getUsage());
            energyHist.setAssociatedTime(new Date());
            energyHist.setModuleId(lamp.getModuleId());
            energyHist.setGzoneId(lamp.getGeoZoneId());
            histRepository.save(energyHist);
        });
    }
}
