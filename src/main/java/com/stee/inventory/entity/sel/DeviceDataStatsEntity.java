package com.stee.inventory.entity.sel;

import com.stee.sel.lim.reading.CommonUtils;

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
	@Id
	@SequenceGenerator(name = "device_data_stats_seq_id", sequenceName = "device_data_stats_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_data_stats_seq_id")
	private Integer id;
	/**
	 * eg:Luminaire, CMS-App
	 */
	@Column(name="data_source")
	private String dataSource;
	/**
	 * The timestamp at which device data was generated
	 */
	@Column(name="createTime")
	private Timestamp createTime;
	/**
	 * device id
	 */
	@Column(name="device_id")
	private String deviceId;
	/**
	 * eg:burning hour;Current, Voltage, Power Factor, Reactive Power, Active Power
	 */
	@Column(name="data_type")
	private Integer dataType;
	/**
	 * data value
	 */
	@Column(name="data")
	private Double data;
	/**
	 * tenant Id
	 */
	@Column(name="tenant_id")
	private Integer tenantId;
	
	@Transient
	private String formatDate;
	
	/******************set get********************/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		this.formatDate = CommonUtils.df.format(createTime);
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public Double getData() {
		return data;
	}
	public void setData(Double data) {
		this.data = data;
	}
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	public String getFormatDate() {
		return formatDate;
	}
	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}
	@Override
	public String toString() {
		return "DeviceDataHistEntity [id=" + id + ", dataSource=" + dataSource + ", createTime=" + createTime
				+ ", deviceId=" + deviceId + ", dataType=" + dataType + ", data=" + data + ", tenantId=" + tenantId
				+ "]";
	}
	
}
