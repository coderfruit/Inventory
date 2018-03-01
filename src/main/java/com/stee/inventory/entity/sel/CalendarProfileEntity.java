package com.stee.inventory.entity.sel;

import java.util.Date;
import java.util.List;

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

@Entity
@Table(name="stl_calendar_profile")
public class CalendarProfileEntity {
	/**
	 * PK id
	 */
	@Id
	@SequenceGenerator(name = "calendar_profile_seq_id", sequenceName = "calendar_profile_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendar_profile_seq_id")
	private Integer calendarProfileId;
	
	/**
	 * name
	 */
	@Column(name="name", nullable = false)
	private String name;
	
	/**
	 * incipient time
	 */
	@Column(name="start_date", nullable = false)
	private Date startDate;
	
	/**
	 * Perform the end time
	 */
	@Column(name="end_date", nullable = false)
	private Date endDate;
	
	/**
	 * modify time
	 */
	@Column(name="last_updated_time")
	private Date lastUpdatedTime;
	
	/**
	 * create time
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * status
	 */
	@Column(name="status")
	private String status;
	
	/**
	 * description about CalendarProfile
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * tenantId
	 */
	@Column(name="tenant_id")
	private Integer tenantId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "calendar_profile_id")
	private List<CalendarDailyprofileEntity> rules;
	
	/**
	 * Make a distinction between front-end requests.
	 */
	@Transient
	private String type;


	/********************set get*********************/

	public Integer getCalendarProfileId() {
		return calendarProfileId;
	}

	public void setCalendarProfileId(Integer calendarProfileId) {
		this.calendarProfileId = calendarProfileId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	
	public List<CalendarDailyprofileEntity> getRules() {
		return rules;
	}

	public void setRules(List<CalendarDailyprofileEntity> rules) {
		this.rules = rules;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CalendarProfileEntity [calendarProfileId=" + calendarProfileId + ", name=" + name + ", startDate="
				+ startDate + ", endDate=" + endDate + ", lastUpdatedTime=" + lastUpdatedTime + ", createTime="
				+ createTime + ", status=" + status + ", description=" + description + ", tenantId=" + tenantId
				+ ", rules=" + rules + ", type=" + type + "]";
	}

	
}
