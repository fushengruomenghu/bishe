package com.hyl.bishe.service;

import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SchoolInfoService {
    Page<SchoolInfo> findAll(Example example,Pageable Page);
    List<String> findLocation();
    List<String> findType();
    List<String> findLevel();
}
