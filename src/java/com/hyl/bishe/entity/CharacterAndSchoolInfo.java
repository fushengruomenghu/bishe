package com.hyl.bishe.entity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//@Data
//@AllArgsConstructor
//@JsonSerialize
public class CharacterAndSchoolInfo  {
    /**
     * 自定义的实体
     */

    private static final long serialVersionUID = -6347911007178390219L;

    String province;
    String majorname;
    String college;
    String location;
    String leibie;

    String grade;
    String batch;
    String comprehensive;
    String environment;
    String life;
}
