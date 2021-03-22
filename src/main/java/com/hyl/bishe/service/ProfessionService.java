package com.hyl.bishe.service;

import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.Scoreline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfessionService {
    Page<Profession> findAll(Pageable page);
}
