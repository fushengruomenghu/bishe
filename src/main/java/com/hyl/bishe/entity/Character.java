package com.hyl.bishe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity(name = "characteristic")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String grade;

    @Column
    private String province;

    @Column
    private String location;

    @Column
    private String leibie;

    @Column
    private String batch;

    @Column
    private String college;

    @Column
    private String majorname;


    private Integer sid;

    private Integer pid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    //
//
//    @ManyToOne(fetch=FetchType.EAGER,targetEntity=SchoolInfo.class,cascade=CascadeType.ALL,optional = false)
//    @JoinColumn(name="sid",referencedColumnName="id",unique = true)
//    private SchoolInfo schoolInfo;
//    @ManyToOne(fetch=FetchType.LAZY,targetEntity=Profession.class,cascade=CascadeType.ALL,optional = false)
//    @JoinColumn(name="pid",referencedColumnName="id",unique = true)
//    private Profession profession;

//    public SchoolInfo getSchoolInfo() {
//        return schoolInfo;
//    }
//
//    public void setSchoolInfo(SchoolInfo schoolInfo) {
//        this.schoolInfo = schoolInfo;
//    }
//
//    public Profession getProfession() {
//        return profession;
//    }
//
//    public void setProfession(Profession profession) {
//        this.profession = profession;
//    }


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
