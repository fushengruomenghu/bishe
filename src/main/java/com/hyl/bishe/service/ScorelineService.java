package com.hyl.bishe.service;

import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Scoreline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ScorelineService {
    Page<Scoreline> findAll(Specification<Scoreline> scorelineSpecification, Pageable page);

    List<String> findProvince();
    List<String> findYear();
    List<String> findleibie();
    List<String> findpici();
}
