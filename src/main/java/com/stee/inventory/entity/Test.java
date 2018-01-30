package com.stee.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stl_gzm_gzoneandlamprelation")
public class Test {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "gzone_id")
    private String gzoneid;

    @Column(name = "lamp_id")
    private String lampid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGzoneid() {
        return gzoneid;
    }

    public void setGzoneid(String gzoneid) {
        this.gzoneid = gzoneid;
    }

    public String getLampid() {
        return lampid;
    }

    public void setLampid(String lampid) {
        this.lampid = lampid;
    }

    public Test(String id, String gzoneid, String lampid) {
        this.id = id;
        this.gzoneid = gzoneid;
        this.lampid = lampid;
    }

    public Test() {
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", gzoneid='" + gzoneid + '\'' +
                ", lampid='" + lampid + '\'' +
                '}';
    }
}
