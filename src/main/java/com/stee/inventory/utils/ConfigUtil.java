package com.stee.inventory.utils;

import com.stee.sel.common.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * File Name    : ConfigUtil.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月28日 下午3:44:41
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *
 */

@Component
public class ConfigUtil {
	
	public static final String SUCCESS = "000000";
	
	public static final String FAILED = "999999";   //处理失败
	
	public static final String ENCODING_NAME="UTF-8";


	public static String ATM_SAVELOG_ADDRESS;
//	public static final String ATM_SAVELOG_ADDRESS = PropUtils.getContextProperty("ATM_SAVELOG_ADDRESS").toString();

    @Value("${ATM_SAVELOG_ADDRESS}")
    public void setAtmSavelogAddress(String atmSavelogAddress) {
        ATM_SAVELOG_ADDRESS = atmSavelogAddress;
    }

    public static String getNowTimeString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static ResultData ok(){
		ResultData result = new ResultData();
		result.setStatus(SUCCESS);
		return result;
	}
	
	public static ResultData fail(){
		ResultData result = new ResultData();
		result.setStatus(FAILED);
		return result;
	}
	
	public static ResultData okOnlyOne(Object object){
		List list = new ArrayList();
		list.add(object);
		ResultData result = new ResultData();
		result.setStatus(ConfigUtil.SUCCESS);
		result.setData(list);
		return result;
	}
	
	public static ResultData okList(List list){
		ResultData result = new ResultData();
		result.setStatus(ConfigUtil.SUCCESS);
		result.setData(list);
		return result;
	}

//	@PostConstruct
    public void run(){
        System.out.println("-----------"+ATM_SAVELOG_ADDRESS+"------------------");
    }
}
