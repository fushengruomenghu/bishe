package com.hyl.bishe.dao;

import com.hyl.bishe.entity.SchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolInfoDao extends JpaRepository<SchoolInfo,Integer> {
    SchoolInfo findAllById(Integer id);
    SchoolInfo findSchoolInfoBySchname(String name);
}
