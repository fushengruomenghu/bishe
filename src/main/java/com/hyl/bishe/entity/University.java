package com.hyl.bishe.entity;

import javax.persistence.*;

@Entity(name = "university")
public class University {
    @Id
    Integer id;
    @Column
    String name;
    @Column
    String degreeNum;
    @Column
    String boshidian;
    @Column
    String keySubjects;
    @Column
    String keyLaboratory;
    @Column
    String creationTime;
    @Column
    String area;
    @Column
    String xingzhi;
    @Column
    String stuNum;
    @Column
    String academicianNum;
    @Column
    String telephone;
    @Column
    String schWebsite;
    @Column
    String enrollmentWebsite;

    @OneToOne(fetch= FetchType.LAZY,targetEntity=SchoolInfo.class)
    private SchoolInfo schoolInfo;
    public University() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegreeNum() {
        return degreeNum;
    }

    public void setDegreeNum(String degreeNum) {
        this.degreeNum = degreeNum;
    }

    public String getBoshidian() {
        return boshidian;
    }

    public void setBoshidian(String boshidian) {
        this.boshidian = boshidian;
    }

    public String getKeySubjects() {
        return keySubjects;
    }

    public void setKeySubjects(String keySubjects) {
        this.keySubjects = keySubjects;
    }

    public String getKeyLaboratory() {
        return keyLaboratory;
    }

    public void setKeyLaboratory(String keyLaboratory) {
        this.keyLaboratory = keyLaboratory;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getXingzhi() {
        return xingzhi;
    }

    public void setXingzhi(String xingzhi) {
        this.xingzhi = xingzhi;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getAcademicianNum() {
        return academicianNum;
    }

    public void setAcademicianNum(String academicianNum) {
        this.academicianNum = academicianNum;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSchWebsite() {
        return schWebsite;
    }

    public void setSchWebsite(String schWebsite) {
        this.schWebsite = schWebsite;
    }

    public String getEnrollmentWebsite() {
        return enrollmentWebsite;
    }

    public void setEnrollmentWebsite(String enrollmentWebsite) {
        this.enrollmentWebsite = enrollmentWebsite;
    }

    public SchoolInfo getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(SchoolInfo schoolInfo) {
        this.schoolInfo = schoolInfo;
    }

    public University(Integer id, String name, String degreeNum, String boshidian, String keySubjects, String keyLaboratory, String creationTime, String area, String xingzhi, String stuNum, String academicianNum, String telephone, String schWebsite, String enrollmentWebsite, SchoolInfo schoolInfo) {
        this.id = id;
        this.name = name;
        this.degreeNum = degreeNum;
        this.boshidian = boshidian;
        this.keySubjects = keySubjects;
        this.keyLaboratory = keyLaboratory;
        this.creationTime = creationTime;
        this.area = area;
        this.xingzhi = xingzhi;
        this.stuNum = stuNum;
        this.academicianNum = academicianNum;
        this.telephone = telephone;
        this.schWebsite = schWebsite;
        this.enrollmentWebsite = enrollmentWebsite;
        this.schoolInfo = schoolInfo;
    }
}