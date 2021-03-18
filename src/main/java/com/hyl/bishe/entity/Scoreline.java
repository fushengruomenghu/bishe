package com.hyl.bishe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "score_line")
public class Scoreline {
  @Id
  Integer id;
  @Column
  String location;
  @Column
  String year;
  @Column
  String leibie;
  @Column
  String pici;
  @Column
  String score;

    public Scoreline(Integer id, String location, String year, String leibie, String pici, String score) {
        this.id = id;
        this.location = location;
        this.year = year;
        this.leibie = leibie;
        this.pici = pici;
        this.score = score;
    }

    public Scoreline() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
