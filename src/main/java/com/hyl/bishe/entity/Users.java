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
    @Column

    String sex;
    @Column

    String email;

    @Column
    String phone;

    public Users(String username, String password, String sex, String email, String phone) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
    }

    public Users() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +

                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
