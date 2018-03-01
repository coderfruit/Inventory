package com.stee.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stl_co2_emission_factor")
public class Co2EmissionFactor implements Serializable{
    private Integer co2EmissionFactorId;
    private String area;
    private Double co2EmissionFactor;
    private String country;
    private String publishedYear;
    private Integer tenantId;

    @Id
    @Column(name = "co2_emission_factor_id")
    public Integer getCo2EmissionFactorId() {
        return co2EmissionFactorId;
    }

    public void setCo2EmissionFactorId(Integer co2EmissionFactorId) {
        this.co2EmissionFactorId = co2EmissionFactorId;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "co2_emission_factor")
    public Double getCo2EmissionFactor() {
        return co2EmissionFactor;
    }

    public void setCo2EmissionFactor(Double co2EmissionFactor) {
        this.co2EmissionFactor = co2EmissionFactor;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "published_year")
    public String getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Co2EmissionFactor(Integer co2EmissionFactorId, String area, Double co2EmissionFactor, String country, String publishedYear, Integer tenantId) {
        this.co2EmissionFactorId = co2EmissionFactorId;
        this.area = area;
        this.co2EmissionFactor = co2EmissionFactor;
        this.country = country;
        this.publishedYear = publishedYear;
        this.tenantId = tenantId;
    }

    public Co2EmissionFactor() {
    }
}
