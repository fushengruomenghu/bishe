package com.hyl.bishe.dao;

import com.hyl.bishe.entity.SchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolInfoDao extends JpaRepository<SchoolInfo,Integer> {
    SchoolInfo findAllById(Integer id);
    SchoolInfo findSchoolInfoBySchname(String name);
    List<SchoolInfo> findSchoolInfosByLocation(String location);
//    List<SchoolInfo> findSchoolInfosByEducation_level(String education_level);
    List<SchoolInfo> findSchoolInfoByType(String type);

    @Query("SELECT DISTINCT location from schoolinfo")
    List<String> findDistinctLocation();
    @Query("SELECT DISTINCT type from schoolinfo")
    List<String> findDistinctType();
    @Query("SELECT DISTINCT education_level from schoolinfo")
    List<String> findDistinctLevel();

}
