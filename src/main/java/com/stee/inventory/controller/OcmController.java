package com.stee.inventory.controller;

import com.stee.sel.common.ResultData;
import com.stee.inventory.entity.CarboDioxide;
import com.stee.inventory.entity.EnergyManagementConfiguration;
import com.stee.inventory.service.ISvcCarboDioxide;
import com.stee.inventory.service.ISvcEnergyManagementConfiguration;
import com.stee.inventory.service.ISvcLifeTimeTracking;
import com.stee.inventory.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : ocm
 * File Name    : OcmController.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午4:14:10
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@RestController
public class OcmController {
	@Autowired@Qualifier("iSvcCarboDioxide")
	private ISvcCarboDioxide iSvcCarboDioxide;
	@Autowired@Qualifier("iSvcEnergyManagementConfiguration")
	private ISvcEnergyManagementConfiguration iSvcEnergyManagementConfiguration;
	@Autowired@Qualifier("iSvcLifeTimeTracking")
	private ISvcLifeTimeTracking iSvcLifeTimeTracking;
	
	//---------------------------------------------LifeTimeTracking------------
//	@ResponseBody
//	@RequestMapping(path="/lifeTime/save",method=RequestMethod.POST)
//	public ResultData save(@RequestBody LifeTimeTracking lifeTimeTracking){
//		return iSvcLifeTimeTracking.save(lifeTimeTracking);
//	}
//	
//	@ResponseBody
//	@RequestMapping(path="/lifeTime/update",method=RequestMethod.PUT)
//	public ResultData update(@RequestBody LifeTimeTracking lifeTimeTracking){
//		return iSvcLifeTimeTracking.update(lifeTimeTracking);
//	}
//	
//	@ResponseBody
//	@RequestMapping(path="/lifeTime/delete/{id}",method=RequestMethod.DELETE)
//	public ResultData deleteLT(@PathVariable Integer id){
//		return iSvcLifeTimeTracking.deleteLT(id);
//	}
//	
//	@ResponseBody
//	@RequestMapping(path="/lifeTime/get",method=RequestMethod.GET)
//	public ResultData getLtById(@WebParam Integer id){
//		return iSvcLifeTimeTracking.getLTById(id);
//	}
//	
//	@ResponseBody
//	@RequestMapping(path="/lifeTime/getall",method=RequestMethod.GET)
//	public ResultData getAllLT(){
//		return iSvcLifeTimeTracking.getAllLT();
//	}
	
	
	//-------------------------------------------EnergyManagementConfiguration-----------
	@Log(moduleName="save",desc="保存能源管理配置")
	@ResponseBody
	@RequestMapping(path="/emc/save",method=RequestMethod.POST)
	public ResultData save(@RequestBody EnergyManagementConfiguration carboDioxide){
		return iSvcEnergyManagementConfiguration.save(carboDioxide);
	}
	
	@Log(moduleName="update",desc="修改能源管理配置")
	@ResponseBody
	@RequestMapping(path="/emc/update",method=RequestMethod.PUT)
	public ResultData update(@RequestBody EnergyManagementConfiguration carboDioxide){
		return iSvcEnergyManagementConfiguration.update(carboDioxide);
	}
	
	@Log(moduleName="deleteEMC",desc="删除能源管理配置")
	@ResponseBody
	@RequestMapping(path="/emc/delete/{id}",method=RequestMethod.DELETE)
	public ResultData deleteEMC(@PathVariable Integer id){
		return iSvcEnergyManagementConfiguration.deleteEMC(id);
	}
	
	@ResponseBody
	@RequestMapping(path="/emc/get",method=RequestMethod.GET)
	public ResultData getEMCById(@WebParam Integer id){
		return iSvcEnergyManagementConfiguration.getEMCById(id);
	}
	
	@ResponseBody
	@RequestMapping(path="/emc/getall",method=RequestMethod.GET)
	public ResultData getAllEMC(){
		return iSvcEnergyManagementConfiguration.getAllEMC();
	}
	
	@ResponseBody
	@RequestMapping(path="/emc/fuzzy/get",method=RequestMethod.GET)
	public ResultData fuzzyQueryByEMCName(@WebParam String name){
		return iSvcEnergyManagementConfiguration.fuzzyQueryByName(name);
	}
	
	//------------------------------------------------CarboDioxide----------------------
	@Log(moduleName="save",desc="保存二氧化碳设置信息")
	@ResponseBody
	@RequestMapping(path="/co2/save",method=RequestMethod.POST)
	public ResultData save(@RequestBody CarboDioxide carboDioxide){
		return iSvcCarboDioxide.save(carboDioxide);
	}
	
	@Log(moduleName="update",desc="修改二氧化碳设置信息")
	@ResponseBody
	@RequestMapping(path="/co2/update",method=RequestMethod.PUT)
	public ResultData update(@RequestBody CarboDioxide carboDioxide){
		return iSvcCarboDioxide.update(carboDioxide);
	}
	
	@Log(moduleName="deleteCD",desc="删除二氧化碳设置信息")
	@ResponseBody
	@RequestMapping(path="/co2/delete/{id}",method=RequestMethod.DELETE)
	public ResultData deleteCD(@PathVariable Integer id){
		return iSvcCarboDioxide.deleteCD(id);
	}
	
	@ResponseBody
	@RequestMapping(path="/co2/get",method=RequestMethod.GET)
	public ResultData getCDByName(@WebParam String name){
		return iSvcCarboDioxide.getCDByName(name);
	}
	
	@ResponseBody
	@RequestMapping(path="/co2/getall",method=RequestMethod.GET)
	public ResultData getAllCD(){
		return iSvcCarboDioxide.getAllCD();
	}
	
	@ResponseBody
	@RequestMapping(path="/co2/fuzzy/get",method=RequestMethod.GET)
	public ResultData fuzzyQueryByName(@WebParam String name){
		return iSvcCarboDioxide.fuzzyQueryByName(name);
	}
}
