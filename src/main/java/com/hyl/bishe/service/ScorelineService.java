package com.hyl.bishe.service;

import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Scoreline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScorelineService {
    Page<Scoreline> findAll(Pageable page);
}
