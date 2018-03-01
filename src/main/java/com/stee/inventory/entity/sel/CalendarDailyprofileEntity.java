package com.stee.inventory.entity.sel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="stl_calendar_dailyprofile")
public class CalendarDailyprofileEntity {
	/**
	 * PK id
	 */
	@Id
	@SequenceGenerator(name = "calendar_dailyprofile_seq_id", sequenceName = "calendar_dailyprofile_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendar_dailyprofile_seq_id")
	@Column(name="calendar_dailyprofile_id")
	private Integer calendarDailyprofileId;
	
	/**
	 * Start Date
	 */
	@Column(name="start_date")
	private Date startDate;
	
	/**
	 * End Time
	 */
	@Column(name="end_date")
	private Date endDate;
	
	/**
	 * Daily Profile Id
	 */
	@Column(name="daily_profile_id")
	private Integer dailyProfileId;
	
	/**
	 * Priority
	 */
	@Column(name="priority")
	private Integer priority;
	
	/**
	 * Recurrent Pattern
	 */
	@Column(name="recurrent_pattern")
	private String recurrentPattern;
	
	/**
	 * Tenant Id
	 */
	@Column(name="tenant_id")
	private Integer tenantId;
	
	/**
	 * Make a distinction between front-end requests.
	 */
	@Transient
	private String type;
	
	
	/**************set get*******************/
	public Integer getCalendarDailyprofileId() {
		return calendarDailyprofileId;
	}

	public void setCalendarDailyprofileId(Integer calendarDailyprofileId) {
		this.calendarDailyprofileId = calendarDailyprofileId;
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

	public Integer getDailyProfileId() {
		return dailyProfileId;
	}

	public void setDailyProfileId(Integer dailyProfileId) {
		this.dailyProfileId = dailyProfileId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getRecurrentPattern() {
		return recurrentPattern;
	}

	public void setRecurrentPattern(String recurrentPattern) {
		this.recurrentPattern = recurrentPattern;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "CalendarDailyprofileEntity [calendarDailyprofileId=" + calendarDailyprofileId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", dailyProfileId=" + dailyProfileId + ", priority=" + priority
				+ ", recurrentPattern=" + recurrentPattern + ", tenantId=" + tenantId + ", type=" + type + "]";
	}

	
	
	

}
