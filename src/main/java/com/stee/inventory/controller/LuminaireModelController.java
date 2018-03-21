package com.stee.inventory.controller;

import com.stee.inventory.entity.LuminaireQueryBean;
import com.stee.inventory.service.IDeviceModelService;
import com.stee.sel.common.ResultData;
import com.stee.sel.inventory.DeviceModelEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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
 * Project Name : STL_ASM
 * File Name    : LuminaireModelController.java
 * Author       : Jerry
 * Created      : 2016年11月15日 下午1:37:54
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@RestController
@RequestMapping(value = "/luminaire/model/config")
public class LuminaireModelController {

	@Resource
	private IDeviceModelService luminaireService;

	/**
	 * 获取所有LuminaireModel的配置信息. *待分页
	 *
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResultData<DeviceModelEntity> getAll() {
		return luminaireService.getAll();
	}

	/**
	 * 新增或修改相应的LuminaireModel 的配置.
	 *
	 //	 * @param config
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, String> save(@RequestBody DeviceModelEntity config) {
		Map<String, String> map = new HashMap<>();
		map.put("status", luminaireService.save(config));
		return map;
	}

	// TODO delete...
	@Deprecated
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody DeviceModelEntity config) {
		return luminaireService.save(config);
	}

	/**
	 * 判断ModelId 是否已经存在.
	 *
	 * @param id
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/isIdExits/{id}", method = RequestMethod.GET)
	public boolean isIdExits(@PathVariable("id") String id) {
		return luminaireService.isIdExits(id);
	}

	/**
	 * 删除指定的配置项
	 *
	 * @param id
	 * @return
	 * @author Jerry
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Map<String, String> delete(@PathVariable("id") String id) {
		Map<String, String> map = new HashMap<>();
		map.put("status", luminaireService.delete(id));
		return map;
	}

	/**
	 * Luminaire 模糊查询
	 *
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/query/modelId/like", method = RequestMethod.POST)
	public ResultData<DeviceModelEntity> findByModelIdLike(@RequestBody(required = false) LuminaireQueryBean query) {
		return luminaireService.findByModelIdLike(query);
	}

}
