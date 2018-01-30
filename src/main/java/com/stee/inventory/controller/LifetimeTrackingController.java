package com.stee.inventory.controller;

import com.stee.inventory.entity.QueryBean;
import com.stee.inventory.service.ILifetimeTrackingService;
import com.stee.sel.asm.LifetimeTrackingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
 * File Name    : LifetimeTrackingController.java
 * Author       : Jerry
 * Created      : 2016年12月1日 上午10:22:24
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@RestController
@RequestMapping("/lifetime")
public class LifetimeTrackingController {
	@Autowired
	ILifetimeTrackingService service;

	@RequestMapping(value = "/pagingAndSort", method = RequestMethod.POST)
	public Page<LifetimeTrackingConfig> getByPage(@RequestBody(required = false) QueryBean query,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize,
			@RequestParam(name = "sort", required = false) String direction) {
		System.out.println(pageNo);
		System.out.println(pageSize);
		System.out.println(direction);
		return service.getByPage(query, pageNo, pageSize, direction);
	}

	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public Map<String, String> save(@RequestBody LifetimeTrackingConfig config) {
		System.out.println(config);
		Map<String, String> map = new HashMap<>();
		map.put("status", service.save(config));
		return map;
	}

	@RequestMapping(value = "/isNameExits/{name}", method = RequestMethod.GET)
	public boolean isNameExits(@PathVariable("name") String name) {
		return service.isNameExits(name);
	}

	@RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
	Map<String, String> deleteByName(@PathVariable("id") Integer id) {
        Map<String, String> map = new HashMap<>();
		map.put("status", service.deleteByName(id));
		return map;
	}

}
