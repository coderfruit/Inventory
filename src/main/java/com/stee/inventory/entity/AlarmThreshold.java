package com.stee.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stl_alarm_threshold")
public class AlarmThreshold implements Serializable{
    private Integer alarmThresholdId;
    private String name;
    private String description;
    private String dataType;
    private Double thresholdValue;
    private String thresholdRule;
    private Integer thresholdLevel;
    private String alarmMessage;
    private Integer tenantId;

    @Id
    @Column(name = "alarm_threshold_id")
    public Integer getAlarmThresholdId() {
        return alarmThresholdId;
    }

    public void setAlarmThresholdId(Integer alarmThresholdId) {
        this.alarmThresholdId = alarmThresholdId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "data_type")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "threshold_value")
    public Double getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(Double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    @Basic
    @Column(name = "threshold_rule")
    public String getThresholdRule() {
        return thresholdRule;
    }

    public void setThresholdRule(String thresholdRule) {
        this.thresholdRule = thresholdRule;
    }

    @Basic
    @Column(name = "threshold_level")
    public Integer getThresholdLevel() {
        return thresholdLevel;
    }

    public void setThresholdLevel(Integer thresholdLevel) {
        this.thresholdLevel = thresholdLevel;
    }

    @Basic
    @Column(name = "alarm_message")
    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public AlarmThreshold(Integer alarmThresholdId, String name, String description, String dataType, Double thresholdValue, String thresholdRule, Integer thresholdLevel, String alarmMessage, Integer tenantId) {
        this.alarmThresholdId = alarmThresholdId;
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.thresholdValue = thresholdValue;
        this.thresholdRule = thresholdRule;
        this.thresholdLevel = thresholdLevel;
        this.alarmMessage = alarmMessage;
        this.tenantId = tenantId;
    }

    public AlarmThreshold() {
    }
}
