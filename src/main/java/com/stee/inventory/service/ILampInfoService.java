package com.stee.inventory.service;

import com.stee.inventory.dto.*;
import com.stee.sel.asm.LuminaireModelConfig;
import com.stee.sel.common.ResultData;
import com.stee.sel.lim.LampInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_LIM
 * File Name    : ILampInfoService.java
 * Author       : Jerry
 * Created      : 2016年10月15日 上午10:59:41
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public interface ILampInfoService {
	LampInfo update(LampInfo info);

	void delete(String id);

	ResultData<LampInfo> getAll();

	String deleteByGgz(String geoZoneId);

	String updateGz(Map<String, String> map);

	void updatePolling(List<LampInfo> list);

	List<LampInfo> getByGeoZoneId(String geoZoneId);
	/**
	 * 获取所有Lamp信息
	 * @param pageNo
	 * @param pageSize
	 * @param type
	 * @param name
	 * @return
	 */
	Page<LampInfo> getAllByFilter(Integer pageNo, Integer pageSize, String type, String name);

	boolean isIdExits(String id);

    LampInfoDetail findLampDetailInfo(String id);
    
    LuminaireModelConfig findLuminaireModelConfig(String id);
    
    Result findPoleOrLuminaire(RequestObject obj);
    /**
     * Batch Delete Lamp
     * @param ids
     * @return
     */
    Result batchDelete(List<String> ids);
    /**
     * update Lamp Info
     * @param deviceInfo
     * @return
     */
    Result updateDeviceInfo(DeviceInfo deviceInfo);
    /**
     * update Lamp Location Info
     * @param deviceLocationInfo
     * @return
     */
    Result updateDeviceInfo(DeviceLocationInfo deviceLocationInfo);
    /**
     * find Device
     * @param deviceId
     * @return
     */
    boolean findLampStatus(String deviceId);
    /**
     * 获取表头
     * @param appactionName
     * @return
     */
	public Map<String,List<String>> getFieldConfig(String appactionName);
	
	
	Result findLuminaireReport(RequestObject obj);
	/**
	 * Export device status information
	 * @param obj
	 * @return
	 */
	Result<String> exportDevcieStatusReport(RequestObject obj);

}
