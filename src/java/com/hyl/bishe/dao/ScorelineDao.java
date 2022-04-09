package com.hyl.bishe.dao;

import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Scoreline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScorelineDao extends JpaRepository<Scoreline,Integer>, JpaSpecificationExecutor<Scoreline>{
    @Query("SELECT DISTINCT location from score_line")
    List<String> findDistinctLocation();
    @Query("SELECT DISTINCT pici from score_line")
    List<String> findDistinctPici();
    @Query("SELECT DISTINCT leibie from score_line")
    List<String> findDistinctLeibie();
    @Query("SELECT DISTINCT year from score_line")
    List<String> findDistinctYear();

    Page<Scoreline> findAll(Specification<Scoreline> scorelineSpecification, Pageable page);

}
