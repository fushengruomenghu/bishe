package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.ProfessionDao;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionDao professionDao;
    @Override
    public Page<Profession> findAll(Specification<Profession> professionSpecification ,Pageable page) {
        return professionDao.findAll(professionSpecification,page);
    }

    public List<Profession> findAll() {
        return professionDao.findAll();
    }


    @Override
    public List<String> findMenglei(String menglei) {
        return professionDao.findDistinctDisciplines(menglei);
    }

    public Profession findbyName(String name){
        return professionDao.findProfessionByProname(name);
    }

   public List<String> findProNameByDegreeCategory(String str1,String str2,String str3){
        return professionDao.findProNameByDegreeCategory(str1,str2,str3);
    }
    
}
