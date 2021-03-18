package com.hyl.bishe.entity;

import javax.persistence.*;

@Entity(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column
    String username;
    @Column
    String school;
    @Column
    String major;

    public Record(Integer id, String username, String school, String major) {
        this.id = id;
        this.username = username;
        this.school = school;
        this.major = major;
    }

    public Record() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
