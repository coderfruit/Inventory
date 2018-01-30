package com.stee.inventory.utils;

import java.util.ResourceBundle;

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
 * File Name    : ResourceFetchingUtils.java
 * Author       : Jerry
 * Created      : 2016年11月16日 下午10:01:02
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public class ResourceFetchingUtils {
	public static String getValue(String key) {
		String value = null;
		ResourceBundle bundle;
		try {
			bundle = ResourceBundle.getBundle("config");
			value = bundle.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
