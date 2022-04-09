package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.SchoolInfoDao;
import com.hyl.bishe.dao.UniversityDao;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.University;
import com.hyl.bishe.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolInfoServiceImpl implements SchoolInfoService {
    @Autowired
    private SchoolInfoDao schoolInfoDao;
    @Autowired
    private UniversityDao universityDao;

    public SchoolInfo findAllById(Integer id){
        return schoolInfoDao.findAllById(id);
    }
    public University findAllByName(String name){
        return universityDao.findAllByName(name);
    }


    public SchoolInfo findByName(String name){
        return schoolInfoDao.findSchoolInfoBySchname(name);
    }


    public List<SchoolInfo> findAll() {
        return schoolInfoDao.findAll();
    }

    public Page<SchoolInfo> findAll(Specification<SchoolInfo> schoolinfoSpecification, Pageable Page) {
        return schoolInfoDao.findAll(schoolinfoSpecification,Page);
    }
    public List<SchoolInfo> findbyType(String type){return schoolInfoDao.findSchoolInfoByType(type);}
//    public List<SchoolInfo> findbyEducation_level(String level){return schoolInfoDao.findSchoolInfosByEducation_level(level);}
    public List<SchoolInfo> findbyLocation(String location){return schoolInfoDao.findSchoolInfosByLocation(location);}

    @Override
    public Page<SchoolInfo> findAll(Example example, Pageable Page) {
        return null;
    }

    @Override
    public List<String> findLocation() {
        return schoolInfoDao.findDistinctLocation();
    }

    @Override
    public List<String> findType() {
        return schoolInfoDao.findDistinctType();
    }

    @Override
    public List<String> findLevel() {
        return schoolInfoDao.findDistinctLevel();
    }
}
