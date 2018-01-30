package com.stee.inventory.repository.impl;

import com.stee.inventory.entity.LifeTimeTracking;
import com.stee.inventory.repository.BaseDao;
import com.stee.inventory.repository.ILifeTimeTrackingDao;
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
 * File Name    : LifeTimeTrackingDaoImpl.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:18:03
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Repository("iLifeTimeTrackingDao")
public class LifeTimeTrackingDaoImpl extends BaseDao implements ILifeTimeTrackingDao{

	@Override
	public void save(LifeTimeTracking lifeTimeTracking) {
		getCurrentSession().save(lifeTimeTracking);
	}

	@Override
	public void update(LifeTimeTracking lifeTimeTracking) {
		getCurrentSession().update(lifeTimeTracking);
	}

	@Override
	public void deleteLT(Integer id) {
		getCurrentSession().createQuery("delete from LifeTimeTracking where id = ?").
		setInteger(0, id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LifeTimeTracking> getLTById(Integer id) {
		return (List<LifeTimeTracking>)getCurrentSession().
					createQuery("from LifeTimeTracking where id = ?")
					.setInteger(0, id).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LifeTimeTracking> getAllLT() {
		return (List<LifeTimeTracking>)getCurrentSession().
				createQuery("from LifeTimeTracking").list();
	}

}
