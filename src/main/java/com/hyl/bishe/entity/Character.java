package com.hyl.bishe.entity;

import javax.persistence.*;

@Entity(name = "characteristic")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column
    String grade;
    @Column
    String province;
    @Column
    String location;
    @Column
    String leibie;
    @Column
    String batch;
    @Column
    String college;
    @Column
    String majorname;

    public Character(Integer id, String grade, String province, String location, String leibie, String batch, String college, String majorname) {
        this.id = id;
        this.grade = grade;
        this.province = province;
        this.location = location;
        this.leibie = leibie;
        this.batch = batch;
        this.college = college;
        this.majorname = majorname;
    }

    public Character() {

    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLeibie() {
        return leibie;
    }

    public void setLeibie(String leibie) {
        this.leibie = leibie;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
