package com.hyl.bishe.entity;

import javax.persistence.*;

@Entity(name = "pro_info")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column
    String ranks;
    @Column(name = "pro_name")
    String proname;
    @Column
    String degree_category;
    @Column
    String pro_category;
    @Column
    String disciplines;
    @Column
    String pro_code;
    @Column
    String degree;
    @Column
    String years;

    public Profession() {

    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public Profession(Integer id, String ranks, String proname, String degree_category, String pro_category, String disciplines, String pro_code, String degree, String years) {
        this.id = id;
        this.ranks = ranks;
        this.proname = proname;
        this.degree_category = degree_category;
        this.pro_category = pro_category;
        this.disciplines = disciplines;
        this.pro_code = pro_code;
        this.degree = degree;
        this.years = years;
    }

    public String getPro_code() {
        return pro_code;
    }

    public void setPro_code(String pro_code) {
        this.pro_code = pro_code;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String pro_name) {
        this.proname = pro_name;
    }

    public String getDegree_category() {
        return degree_category;
    }

    public void setDegree_category(String degree_category) {
        this.degree_category = degree_category;
    }

    public String getPro_category() {
        return pro_category;
    }

    public void setPro_category(String pro_category) {
        this.pro_category = pro_category;
    }
}
