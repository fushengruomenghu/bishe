package com.hyl.bishe.entity;

import javax.persistence.*;



@Entity(name = "p_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String username;
    @Column
    String password;
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
    @Column
    String major;
    @Column
    String usersrole;
    @Column
    String secret;

    public String getUsersrole() {
        return usersrole;
    }

    public void setUsersrole(String usersrole) {
        this.usersrole = usersrole;
    }

    public Users() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Users(Integer id, String username, String password, String sex, String phone, String location, String grade, String leibie, String major, String usersrole, String secret) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.location = location;
        this.grade = grade;
        this.leibie = leibie;
        this.major = major;
        this.usersrole = usersrole;
        this.secret = secret;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

}
