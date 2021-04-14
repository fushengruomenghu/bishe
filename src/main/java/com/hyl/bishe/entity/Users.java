package com.hyl.bishe.entity;

import javax.persistence.*;



@Entity(name = "p_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String username;
    @Column(insertable = false,columnDefinition = "varchar default '0'")
    String sex;
    @Column
    String phone;
    @Column
    String location;
    @Column
    String grade;
    @Column(insertable = false,columnDefinition = "varchar default '0'")
    String leibie;
    @Column(insertable = false,columnDefinition = "varchar default '0'")
    String pici;
    @Column
    String hobby;


    public Users() {

    }

    public Users(Integer id, String username, String sex, String phone, String location, String grade, String leibie, String pici, String hobby) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.phone = phone;
        this.location = location;
        this.grade = grade;
        this.leibie = leibie;
        this.pici = pici;
        this.hobby = hobby;
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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLeibie() {
        return leibie;
    }

    public void setLeibie(String leibie) {
        this.leibie = leibie;
    }

    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }
}
