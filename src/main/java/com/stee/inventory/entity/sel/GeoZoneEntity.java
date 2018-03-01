package com.stee.inventory.entity.sel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vividsolutions.jts.geom.Geometry;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_SEL
 * File Name    : GeoZoneEntity.java
 * Author       : yuxiaolin
 * Created      : 2018年02月05日 下午4:10:18
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

@Entity
@Table(name = "stl_geozone")
public class GeoZoneEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8022826651581676830L;
	/**
	 * Geo Zone Id
	 */
	@Id
	@SequenceGenerator(name = "stl_geozone_seq_id", sequenceName = "stl_geozone_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stl_geozone_seq_id")
	@Column(name = "geozone_id")
	private Integer geozoneId;
	/**
	 * Geo Zone Area
	 */
	@Column(name = "area")
	private String area;
	/**
	 * Geo Zone Name
	 */
	@Column(name = "name")
	private String name;
	/**
	 * Geo Zone Description
	 */
	@Column(name="description",length=255)
	private String description;
	/**
	 * Other
	 */
	@Column(name = "other")
	private String other;
//	/**
//	 * Dimming Group Id
//	 */
//	@Column(name = "dimming_group_id")
//	private Integer dimmingGroupId;
	/**
	 * Country
	 */
	@Column(name="country")
	private String country;
	/**
	 * City
	 */
	@Column(name="city")
	private String city;
	/**
	 * Parent Id
	 */
	@Column(name="parent_id")
	private String parentId;
	/**
	 * Geo Zone Coordinates
	 */
//	@Column(name="geozone_coordinates")
	@Transient
	private Geometry coordinates;
	
	
	/*******************************set get**********************************/
	public Integer getGeozoneId() {
		return geozoneId;
	}
	public void setGeozoneId(Integer geozoneId) {
		this.geozoneId = geozoneId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
//	public Integer getDimmingGroupId() {
//		return dimmingGroupId;
//	}
//	public void setDimmingGroupId(Integer dimmingGroupId) {
//		this.dimmingGroupId = dimmingGroupId;
//	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Geometry getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Geometry coordinates) {
		this.coordinates = coordinates;
	}
	
	public GeoZoneEntity() {
		super();
	}
	public GeoZoneEntity(Integer geozoneId, String area, String name, String description, String other,
			 String country, String city, String parentId, Geometry coordinates) {
		super();
		this.geozoneId = geozoneId;
		this.area = area;
		this.name = name;
		this.description = description;
		this.other = other;
//		this.dimmingGroupId = dimmingGroupId;
		this.country = country;
		this.city = city;
		this.parentId = parentId;
		this.coordinates = coordinates;
	}
	@Override
	public String toString() {
		return "GeoZoneEntity [geozoneId=" + geozoneId + ", area=" + area + ", name=" + name + ", description="
				+ description + ", other=" + other + ", country=" + country
				+ ", city=" + city + ", parentId=" + parentId + ", coordinates=" + coordinates + "]";
	}


}
