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
 * File Name    : LampInfoDetail
 * Author       : Jerry
 * Created      : 2017/1/10
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
package com.stee.inventory.entity.sel;

import com.stee.sel.inventory.DeviceInfoEntity;
import com.stee.sel.inventory.DeviceModelEntity;
import com.stee.sel.inventory.LampPoleEntity;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public class LampInfoDetail {

    /**
     *
     */
    private DeviceInfoEntity lampInfo;

    /**
     * 灯柱
     */
    private LampPoleEntity pole;

    /**
     * 灯具
     */
    private DeviceModelEntity luminaire;

    public DeviceInfoEntity getLampInfo() {
        return lampInfo;
    }

    public void setLampInfo(DeviceInfoEntity lampInfo) {
        this.lampInfo = lampInfo;
    }

    public LampPoleEntity getPole() {
        return pole;
    }

    public void setPole(LampPoleEntity pole) {
        this.pole = pole;
    }

    public DeviceModelEntity getLuminaire() {
        return luminaire;
    }

    public void setLuminaire(DeviceModelEntity luminaire) {
        this.luminaire = luminaire;
    }

    @Override
    public String toString() {
        return "LampInfoDetail{" +
                "lampInfo=" + lampInfo +
                ", pole=" + pole +
                ", luminaire=" + luminaire +
                '}';
    }
}
