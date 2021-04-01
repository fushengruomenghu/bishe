package com.hyl.bishe.service;

import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.Scoreline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfessionService {
    Page<Profession> findAll(Pageable page);
    Page<Profession> findByCondition(Integer page,  Integer size, String disciplines, String pro_category);
    List<String> findMenglei(String menglei);
}
