package com.stee.inventory.repository.impl;

import com.stee.inventory.entity.CarboDioxide;
import com.stee.inventory.repository.BaseDao;
import com.stee.inventory.repository.ICarboDioxideDao;
import org.springframework.stereotype.Repository;

import java.util.List;

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
 * File Name    : CarboDioxideDaoImpl.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:23:13
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *
 */

@Repository("iCarboDioxideDao")
public class CarboDioxideDaoImpl extends BaseDao implements ICarboDioxideDao{

	@Override
	public void save(CarboDioxide carboDioxide) {
		getCurrentSession().save(carboDioxide);
	}

	@Override
	public void update(CarboDioxide carboDioxide) {
		getCurrentSession().update(carboDioxide);
	}

	@Override
	public void deleteCD(Integer id) {
		getCurrentSession().createQuery("delete from CarboDioxide where id = :id").
		setParameter("id", id).executeUpdate();
	}

	@Override
	public CarboDioxide getCDByName(String name) {
		return (CarboDioxide)getCurrentSession().
				createQuery("from CarboDioxide where area = :name")
				.setParameter("name", name).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarboDioxide> getAllCD() {
		return (List<CarboDioxide>)getCurrentSession().
				createQuery("from CarboDioxide").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarboDioxide> fuzzyQueryByName(String fuzzyName) {
		return (List<CarboDioxide>)getCurrentSession().createQuery("from CarboDioxide where area like :name")
			.setParameter("name", fuzzyName).list();
	}

}
