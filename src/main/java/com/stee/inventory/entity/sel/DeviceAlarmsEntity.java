package com.stee.inventory.entity.sel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stl_device_alarms", schema = "public", catalog = "streetlight")
public class DeviceAlarmsEntity {
    private String deviceAlarmsId;
    private Date createTime;
    private String source;
    private Integer severityLevel;
    private String description;
    private String owner;
    private String category;
    private Integer tenantId;

    @Id
    @Column(name = "device_alarms_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getDeviceAlarmsId() {
        return deviceAlarmsId;
    }

    public void setDeviceAlarmsId(String deviceAlarmsId) {
        this.deviceAlarmsId = deviceAlarmsId;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "severity_level")
    public Integer getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(Integer severityLevel) {
        this.severityLevel = severityLevel;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }


}
