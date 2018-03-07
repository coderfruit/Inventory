package com.stee.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "stl_lamp_pole_model")
public class LampPoleModel{
    private String lampPoleModelId;
    private String description;
    private Double height;
    private String picture;
    private Integer tenantId;

    private List<LampPole> lampPoles;

    @Id
    @Column(name = "lamp_pole_model_id")
    public String getLampPoleModelId() {
        return lampPoleModelId;
    }

    public void setLampPoleModelId(String lampPoleModelId) {
        this.lampPoleModelId = lampPoleModelId;
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
    @Column(name = "height")
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    @OneToMany(mappedBy = "lampPoleModel",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "lampPoleModel")
    public List<LampPole>  getLampPoles() {
        return lampPoles;
    }

    public void setLampPoles(List<LampPole> lampPoles) {
        this.lampPoles = lampPoles;
    }

    public LampPoleModel(String lampPoleModelId, String description, Double height, String picture, Integer tenantId) {
        this.lampPoleModelId = lampPoleModelId;
        this.description = description;
        this.height = height;
        this.picture = picture;
        this.tenantId = tenantId;
    }

    public LampPoleModel() {
    }

    @Override
    public String toString() {
        return "LampPoleModel{" +
                "lampPoleModelId='" + lampPoleModelId + '\'' +
                ", description='" + description + '\'' +
                ", height=" + height +
                ", picture=" + picture +
                ", tenantId=" + tenantId +
                ", lampPoles=" + lampPoles +
                '}';
    }
}
