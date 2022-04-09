package com.hyl.bishe.dao;

import com.hyl.bishe.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityDao extends JpaRepository<University,Integer> {

    University findAllByName(String name);
}
