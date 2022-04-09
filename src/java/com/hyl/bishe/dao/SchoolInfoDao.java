package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Character;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.SchoolInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolInfoDao extends JpaRepository<SchoolInfo,Integer> , JpaSpecificationExecutor<SchoolInfo> {
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

    Page<SchoolInfo> findAll(Specification<SchoolInfo> schoolInfoSpecification, Pageable page);
//    @Query("select s from schoolinfo s where s.")
//    List<SchoolInfo> findSchoolInfoByCharactercollege(String college);
}

