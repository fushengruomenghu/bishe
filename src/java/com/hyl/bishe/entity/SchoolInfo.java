package com.hyl.bishe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    String jiuerproject;

    public String getJiuerproject() {
        return jiuerproject;
    }

    public void setJiuerproject(String jiuerproject) {
        this.jiuerproject = jiuerproject;
    }
    //    @Transient
//    private Integer uid;
//
//    public Integer getUid() {
//        return uid;
//    }
//
//    public void setUid(Integer uid) {
//        this.uid = uid;
//    }
//
//    @OneToOne(fetch= FetchType.EAGER,targetEntity=University.class)
//    @JoinColumn(name="uid",referencedColumnName="id",unique = true)
//    private University university;
//
//    @OneToMany(fetch=FetchType.LAZY,targetEntity=Character.class,mappedBy="schoolInfo",cascade = CascadeType.ALL)
//    private List<Character> characters=new ArrayList<>();

//    public University getUniversity() {
//        return university;
//    }
//
//    public void setUniversity(University university) {
//        this.university = university;
//    }
//
//
//    public List<Character> getCharacters() {
//        return characters;
//    }
//
//    public void setCharacters(List<Character> characters) {
//        this.characters = characters;
//    }

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
