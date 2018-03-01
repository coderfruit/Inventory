package com.stee.inventory.entity.sel;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="stl_dimming_group")
public class DimmingGroupEntity {
	/**
	 * pk ID
	 */
	@Id
	@SequenceGenerator(name = "dimming_group_seq_id", sequenceName = "dimming_group_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dimming_group_seq_id")
	private Integer dimmingGroupId;
	
	/**
	 * DimmingGroup name
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * DimmingGroup description
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * DimmingGroup createTime
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * Calendar Profile Id
	 */
	@Column(name="calendar_profile_id")
	private Integer calendarProfileId;
	/**
	 * Tenant Id
	 */
	@Column(name="tenant_id")
	private Integer tenantId;
	
	@OneToMany(cascade=CascadeType.ALL,targetEntity=GeoZoneEntity.class,fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="dimming_group_id",nullable=true,updatable=true,insertable=true)
	private Set<GeoZoneEntity> geoZoneEntity;
	
	/**
	 * Make a distinction between front-end requests.
	 */
	@Transient
	private String type;
	
	
	/***********************set get*********************/
	
	public Integer getDimmingGroupId() {
		return dimmingGroupId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDimmingGroupId(Integer dimmingGroupId) {
		this.dimmingGroupId = dimmingGroupId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getCalendarProfileId() {
		return calendarProfileId;
	}

	public void setCalendarProfileId(Integer calendarProfileId) {
		this.calendarProfileId = calendarProfileId;
	}

	public Set<GeoZoneEntity> getGeoZoneEntity() {
		return geoZoneEntity;
	}

	public void setGeoZoneEntity(Set<GeoZoneEntity> geoZoneEntity) {
		this.geoZoneEntity = geoZoneEntity;
	}

	@Override
	public String toString() {
		return "DimmingGroupEntity [dimmingGroupId=" + dimmingGroupId + ", name=" + name + ", description="
				+ description + ", createTime=" + createTime + ", calendarProfileId=" + calendarProfileId
				+ ", tenantId=" + tenantId + ", geoZoneEntity=" + geoZoneEntity + ", type=" + type + "]";
	}


}