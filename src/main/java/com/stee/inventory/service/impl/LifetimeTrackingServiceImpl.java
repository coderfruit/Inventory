package com.stee.inventory.service.impl;

import com.stee.inventory.entity.QueryBean;
import com.stee.inventory.repository.LifetimeTrackingRepository;
import com.stee.inventory.service.ILifetimeTrackingService;
import com.stee.sel.asm.LifetimeTrackingConfig;
import com.stee.sel.constant.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
 * File Name    : LifetimeTrackingServiceImpl.java
 * Author       : Jerry
 * Created      : 2016年12月1日 上午10:32:10
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service
public class LifetimeTrackingServiceImpl implements ILifetimeTrackingService {
	@Autowired
	LifetimeTrackingRepository repository;

	@Override
	public String save(LifetimeTrackingConfig config) {
		try {
			repository.save(config);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCode.FAILED.getCode();
		}
		return ResponseCode.SUCCESS.getCode();
	}

	@Override
	public boolean isNameExits(String name) {
		LifetimeTrackingConfig lifetimeTrackingConfig = new LifetimeTrackingConfig();
		lifetimeTrackingConfig.setName(name);
		ExampleMatcher NAME_MATCHER = ExampleMatcher.matching().withMatcher("name",
				GenericPropertyMatchers.ignoreCase());
		Example<LifetimeTrackingConfig> example = Example.<LifetimeTrackingConfig>of(lifetimeTrackingConfig,
				NAME_MATCHER);
		return repository.exists(example);
	}

	@Override
	public Page<LifetimeTrackingConfig> getByPage(QueryBean query, Integer pageNo, Integer pageSize, String direction) {
		PageRequest request = new PageRequest(pageNo == null ? 0 : pageNo, pageSize == null ? 15 : pageSize);
		if (null != query) {
			String query2 = query.getQuery();
			if (null != query2 && !query2.equals("")) {
				return repository.findByNameLike(query2, request);
			} else {
				return repository.findAll(request);
			}
		} else {
			return repository.findAll(request);
		}
	}

	@Override
	public String deleteByName(Integer id) {
		try {
			repository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCode.FAILED.getCode();
		}
		return ResponseCode.SUCCESS.getCode();
	}

}
