package com.stee.inventory.entity.sel;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.stee.sel.report.CommonUtils;
/**
 * 
 * @pSTL_SEL
 * DeviceDataStatsEntity.java
 * @author yuxiaolin
 * @2018年2月24日
 */


@Entity
@Table(name = "stl_device_data_stats")
public class DeviceDataStatsEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4349127048123996613L;
	/**
	 * primary key
	 */

	private Integer id;
	/**
	 * eg:Luminaire, CMS-App
	 */

	private String dataSource;
	/**
	 * The timestamp at which device data was generated
	 */

	private Timestamp createTime;
	/**
	 * device id
	 */

	private String deviceId;
	/**
	 * eg:burning hour;Current, Voltage, Power Factor, Reactive Power, Active Power
	 */

	private Integer dataType;
	/**
	 * data value
	 */

	private Double data;
	/**
	 * tenant Id
	 */

	private Integer tenantId;
	

	private String formatDate;
	
	/******************set get********************/
	@Id
	@SequenceGenerator(name = "device_data_stats_seq_id", sequenceName = "device_data_stats_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_data_stats_seq_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="data_source")
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	@Column(name="create_time")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		this.formatDate = CommonUtils.df.format(createTime);
	}

	@Column(name="device_id")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name="data_type")
	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	@Column(name="data")
	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	@Column(name="tenant_id")
	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	@Transient
	public String getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}
}
