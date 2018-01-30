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
package com.stee.inventory.dto;

import com.stee.sel.asm.PoleModelConfig;
import com.stee.sel.lim.LampInfo;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public class LampInfoDetail {

    /**
     *
     */
    private LampInfo lampInfo;

    /**
     * 灯柱
     */
    private PoleModelConfig pole;

    /**
     * 灯具
     */
    private Luminaire luminaire;

    public LampInfo getLampInfo() {
        return lampInfo;
    }

    public void setLampInfo(LampInfo lampInfo) {
        this.lampInfo = lampInfo;
    }

    public PoleModelConfig getPole() {
        return pole;
    }

    public void setPole(PoleModelConfig pole) {
        this.pole = pole;
    }

    public Luminaire getLuminaire() {
        return luminaire;
    }

    public void setLuminaire(Luminaire luminaire) {
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
