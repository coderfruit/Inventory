package com.stee.inventory.dto;

import com.stee.sel.lim.configruation.Location;

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
 * File Name    : GeoZoneLampInfo.java
 * Author       : Jerry
 * Created      : 2016年11月14日 下午3:34:55
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public class GeoZoneLampInfo {
	private String id;
	private Location location;

	public GeoZoneLampInfo() {
		super();
	}

	public GeoZoneLampInfo(String id, Location location) {
		super();
		this.id = id;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "GeoZoneLampInfo [id=" + id + ", location=" + location + "]";
	}

}
