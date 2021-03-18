package com.hyl.bishe.service;

import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Users;
import org.springframework.data.domain.Page;

public interface SchoolInfoService {
    Page<SchoolInfo> getSchoolInfoList(int pageNum, int pageSize);

}
