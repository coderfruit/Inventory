package com.stee.inventory.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
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
 * File Name    : BaseDao.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:04:39
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

public class BaseDao {
	@Autowired@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public HibernateTemplate getHibernateTemplate(){
		return hibernateTemplate;
	}
}
