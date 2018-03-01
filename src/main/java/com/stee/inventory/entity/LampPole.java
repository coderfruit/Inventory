package com.stee.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stl_lamp_pole")
public class LampPole{
    private String lampPoleId;

    private LampPoleModel lampPoleModel;
    private Integer tenantId;

    @Id
    @Column(name = "lamp_pole_id")
    public String getLampPoleId() {
        return lampPoleId;
    }

    public void setLampPoleId(String lampPoleId) {
        this.lampPoleId = lampPoleId;
    }

    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    @ManyToOne(fetch = FetchType.EAGER,optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="lamp_pole_model_id")
    @JsonIgnoreProperties(value = "lampPoles")
    public LampPoleModel getLampPoleModel() {
        return lampPoleModel;
    }

    public void setLampPoleModel(LampPoleModel lampPoleModel) {
        this.lampPoleModel = lampPoleModel;
    }

    public LampPole(String lampPoleId, Integer tenantId) {
        this.lampPoleId = lampPoleId;
        this.tenantId = tenantId;
    }

    public LampPole() {
    }

    @Override
    public String toString() {
        return "LampPole{" +
                "lampPoleId='" + lampPoleId + '\'' +
                ", lampPoleModel=" + lampPoleModel +
                ", tenantId=" + tenantId +
                '}';
    }
}
