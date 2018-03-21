package com.stee.inventory.controller;

import com.stee.inventory.dto.Result;
import com.stee.inventory.entity.DeviceInfo;
import com.stee.inventory.entity.DeviceLocationInfo;
import com.stee.inventory.entity.RequestObject;
import com.stee.inventory.entity.sel.LampInfoDetail;
import com.stee.inventory.service.ICalendarProfileService;
import com.stee.inventory.service.IDeviceService;
import com.stee.inventory.service.IDimmingGroupService;
import com.stee.inventory.service.IGeoZoneService;
import com.stee.inventory.utils.Utils;
import com.stee.sel.common.ResultData;
import com.stee.sel.gis.GeoZoneEntity;
import com.stee.sel.inventory.DeviceInfoEntity;
import com.stee.sel.inventory.DeviceModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

//import com.stee.inventory.service.ILampInfoService;

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
 * File Name    : LampInformationController.java
 * Author       : Jerry
 * Created      : 2016年10月13日 上午11:41:59
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *
 */
@RestController
@RequestMapping(value = "/lampinfo")
public class LampInformationController {
	@Resource
	private IDeviceService service;

	@Autowired
    ICalendarProfileService cps;

	@Autowired
    IDimmingGroupService dgs;

	@Autowired
    IGeoZoneService gzs;

	private static boolean flag = false;

	@RequestMapping(value = "/asset/import", method = RequestMethod.POST)
	void getAssetFileList(List<String> locations) {

	}

    /**
     * 获取所有数据。
     *
     * @return
     */
	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public ResultData<DeviceInfoEntity> getAll () {
	    return service.getAll();
    }

	/**
	 * 获取过滤后的分页数据。
	 *
	 * @return
	 * @author Yu XiaoLin
	 */
	@RequestMapping(value = "/get/all/byfilter", method = RequestMethod.POST)
	public Page<DeviceInfoEntity> getAllByFilter(@RequestBody RequestObject obj) {
		return service.getAllByFilter(obj.getPageNo(), obj.getPageSize(), obj.getType(), obj.getName());
	}

	/**
	 * 批量删除Lamp.
	 *
	 * @param ids
	 *
	 */
	@RequestMapping(value = "/delete/byIds", method = RequestMethod.POST)
	public Result batchDelete(@RequestBody List<String> ids) {
		return service.batchDelete(ids);
	}

	@RequestMapping(value = "/query/filters", method = RequestMethod.POST)
	public ResultData<DeviceInfoEntity> queryByFilter() {
	    return null;
    }

	/**
	 * 更新Lamp信息。
	 *
	 * @param info
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public DeviceInfoEntity update(@RequestBody DeviceInfoEntity info) {
        DeviceInfoEntity lampInfo = service.update(info);
        System.out.println(lampInfo);
        return lampInfo;
	}

	/**
	 * 删除Lamp.
	 *
	 * @param id
	 * @author Jerry
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

	/**
	 * 更新Lamp 经纬度。
	 *
	 * @param map
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/geozone/devices", method = RequestMethod.POST)
	public String receiveDevices(@RequestBody Map<String, Integer> map) {
		// Key: DeviceId Value:GeozoneId
		System.out.println(map);
		return service.updateGz(map);
	}

	/**
	 * 获取所有Lamp经纬度。 *大数据量，考虑性能问题。
	 *
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/geozone/get/all", method = RequestMethod.GET)
	public ResultData<DeviceInfoEntity> getAllForGz() {
		ResultData<DeviceInfoEntity> all = service.getAll();

		return all;
	}

	/**
	 * 删除指定GeoZone中的设备（仅将LampInfo 对应字段置空）
	 *
	 * @param geoZoneId
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/geozone/delete/{id}", method = RequestMethod.DELETE)
	public String deleteByGz(@PathVariable("id") Integer geoZoneId) {
		return service.deleteByGgz(geoZoneId);
	}

	/**
	 * 获取设备状态是否发生改变。(暂) -- 后改WebSocket 全双工。
	 *
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/statusof/update", method = RequestMethod.GET)
	public boolean fetchStatus() {
		return flag;
	}

	/**
	 * 更新轮询后的实时设备状态。
	 *
	 * @param list
	 * @author Jerry
	 */
	@RequestMapping(value = "/polling/update", method = RequestMethod.POST)
	public void updatePolling(@RequestBody List<DeviceInfoEntity> list) {
		service.updatePolling(list);
		flag = !flag;
	}

	/**
	 * 获取某GeoZone 里面的所有LampInfo
	 *
	 * @param geoZoneId
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/get/lamps/byGeoZoneId/{id}", method = RequestMethod.GET)
	public List<DeviceInfoEntity> getByGeoZoneId(@PathVariable("id") Integer geoZoneId) {
		return service.getByGeoZoneId(geoZoneId);
	}

//	/**
//	 * 资产导入
//	 *
//	 * @param file
//	 * @author Jerry
//	 */
//	@RequestMapping(value = "/upload/asset", consumes = {
//			MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
//	void uploadAsset(@RequestParam("fileUpload") CommonsMultipartFile file) {
//		// TODO Implement indeed.
//	}

//	/**
//	 * 资产导出
//	 *
//	 * @return
//	 * @author Jerry
//	 */
//	@RequestMapping(value = "/download/assets", method = RequestMethod.GET)
//	List<DownloadFile> downloadAssets() {
//		// TODO Implement indeed.
//		return null;
//	}

    /**
     * 判断id是否已经存在
     *
     * @param id
     * @return
     */
	@RequestMapping(value = "/isIdExits", method = RequestMethod.GET)
	boolean isIdExits(@RequestParam(value = "id") String id){
	    return service.isIdExits(id);
    }

    /**
     * 获取Calendar Profile 的Name的集合
     *
     * @return
     */
    @RequestMapping(value = "/calendar/profiles/name/list", method = RequestMethod.GET)
    List<String> getCalendarProfileIds () {
	    return cps.getCalendarProfileIds();
    }

    /**
     * 获取DimmingGroup 的Name的集合
     *
     * @return
     */
    @RequestMapping(value = "/dimming/groups/name/list", method = RequestMethod.GET)
    List<String> getDimmingGroupIds() {
        return dgs.getDimmingGroupIds();
    }

    /**
     * 获取GeoZone 的Name的集合
     *
     * @return
     */
    @RequestMapping(value = "/geozones/name/list", method = RequestMethod.GET)
    List<String> getGeoZoneIds() {
        return gzs.getGeoZoneIds();
    }

    /**
     * 通过名字获取GZone
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/geozone/findByName", method = RequestMethod.POST)
	GeoZoneEntity findGZoneByName(@RequestBody Integer name) {
        return gzs.findGZoneByName(name);
    }

    /**
     * 通过ID获取灯的详细信息
     *
     * @param id
     * @return
     */
	@RequestMapping(value = "/find/byid/detail", method = RequestMethod.GET)
	LampInfoDetail findLampDetailInfo(@RequestParam(name = "id") String id) {
        return service.findLampDetailInfo(id);
    }
	
    /**
     * 通过ID获取灯具控制方式的详细信息
     *
     * @param id
     * @return
     */
	@RequestMapping(value = "/find/byModelId/detail", method = RequestMethod.GET)
	DeviceModelEntity findLuminaireModelConfig(@RequestParam(name = "id") String id){
		return service.findLuminaireModelConfig(id);
	}
	/**
	 * get Lamp detail
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/find/LuminaireDetail", method = RequestMethod.POST)
	Result findPoleOrLuminaire(@RequestBody RequestObject obj){
		return service.findPoleOrLuminaire(obj);
	}
	/**
	 * update device info
	 * @param deviceInfo
	 * @return
	 */
	@RequestMapping(value = "/update/DeviceInfo", method = RequestMethod.POST)
	Result updateDeviceInfo(@RequestBody DeviceInfo deviceInfo){
		return service.updateDeviceInfo(deviceInfo);
	}
	/**
	 * update device location info
	 * @param deviceLocationInfo
	 * @return
	 */
	@RequestMapping(value = "/update/DeviceLocationInfo", method = RequestMethod.POST)
	Result updateDeviceInfo(@RequestBody DeviceLocationInfo deviceLocationInfo){
		return service.updateDeviceInfo(deviceLocationInfo);
	}
	/**
	 * 判断设备是否联网
	 * 
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "find/lampStatus/", method = RequestMethod.GET)
	public boolean findLampStatus(@RequestParam(name = "deviceId") String deviceId){
		return service.findLampStatus(deviceId);
	}
	/**
	 * 获取列
	 * @param appactionName
	 * @return
	 */
	@RequestMapping(value = "/param/fieldConfig/getAll",method = RequestMethod.GET)
	public Map<String,List<String>> getFieldConfig(@RequestParam String appactionName){
		return service.getFieldConfig(appactionName);
	}
	/**
	 * Device Detail Report
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/findReport/LuminaireDetail", method = RequestMethod.POST)
	Result findLuminaireReport(@RequestBody RequestObject obj){
		return service.findLuminaireReport(obj);
	}
	
	/**
	 * Device Detail Report
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/create/deviceStatusReport", method = RequestMethod.POST)
	Result<String> exportDevcieStatusReport(@RequestBody RequestObject obj){
		return service.exportDevcieStatusReport(obj);
	}
	
	
	@RequestMapping(value = "/deviceStatusReport/exportCSV", method = RequestMethod.POST)
	public Result<String> exportCsv(HttpServletResponse response, @RequestParam(name = "csvFileName",required = true) String csvFileName) {
		return Utils.exportCsv(response,csvFileName);
	}

	@RequestMapping(value = "/deviceStatusReport/exportPDF", method = RequestMethod.POST)
	public Result<String> exportPdf(HttpServletResponse response) {
		return Utils.exportPdf(response);
	}
}
