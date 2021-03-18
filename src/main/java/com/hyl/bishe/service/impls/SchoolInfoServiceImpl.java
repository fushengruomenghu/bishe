package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.SchoolInfoDao;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolInfoServiceImpl implements SchoolInfoService {
    @Autowired
    private SchoolInfoDao schoolInfoDao;

    public List<SchoolInfo> findAllSchoolInfo(){
        return schoolInfoDao.findAll();
    }



    @Override
    public Page<SchoolInfo> getSchoolInfoList(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<SchoolInfo> schoolInfos = schoolInfoDao.findAll(pageable);
        return schoolInfos;
    }
}
