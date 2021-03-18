package com.hyl.bishe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "schoolinfo")
public class SchoolInfo {
    @Id
    Integer id;
    @Column
    String schname;
    @Column
    String location;
    @Column
    String belonged;
    @Column
    String type;
    @Column
    String education_level;
    @Column
    String yiliudaxue;
    @Column
    String yiliuxueke;
    @Column
    String graduate_school;
    @Column
    String comprehensive;
    @Column
    String environment;
    @Column
    String life;

    public SchoolInfo(Integer id, String schname, String location, String belonged, String type, String education_level, String yiliudaxue, String yiliuxueke, String graduate_school, String comprehensive, String environment, String life) {
        this.id = id;
        this.schname = schname;
        this.location = location;
        this.belonged = belonged;
        this.type = type;
        this.education_level = education_level;
        this.yiliudaxue = yiliudaxue;
        this.yiliuxueke = yiliuxueke;
        this.graduate_school = graduate_school;
        this.comprehensive = comprehensive;
        this.environment = environment;
        this.life = life;
    }

    public SchoolInfo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchname() {
        return schname;
    }

    public void setSchname(String schname) {
        this.schname = schname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBelonged() {
        return belonged;
    }

    public void setBelonged(String belonged) {
        this.belonged = belonged;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public String getYiliudaxue() {
        return yiliudaxue;
    }

    public void setYiliudaxue(String yiliudaxue) {
        this.yiliudaxue = yiliudaxue;
    }

    public String getYiliuxueke() {
        return yiliuxueke;
    }

    public void setYiliuxueke(String yiliuxueke) {
        this.yiliuxueke = yiliuxueke;
    }

    public String getGraduate_school() {
        return graduate_school;
    }

    public void setGraduate_school(String graduate_school) {
        this.graduate_school = graduate_school;
    }

    public String getComprehensive() {
        return comprehensive;
    }

    public void setComprehensive(String comprehensive) {
        this.comprehensive = comprehensive;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }
}
