package com.hyl.bishe.service;

import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchoolInfoService {
    Page<SchoolInfo> findAll(Pageable Page);
}
