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

    public String findMbtiByName(String name){
        return professionDao.findMbtiByName(name);
    }
    public   String findHollandByName(String name){
        return professionDao.findHollandByName(name);
    }

    public List<Profession> findByLike(String mbti,String holland){
        return professionDao.findByLike(mbti,holland);
    }
    public List<Profession> findByLike(String mbti){
        return professionDao.findByLike(mbti);
    }
    @Override
    public List<String> findMenglei(String menglei) {
        return professionDao.findDistinctDisciplines(menglei);
    }

    public Profession findbyName(String name){
        return professionDao.findProfessionByProname(name);
    }
    public String findMbtiById(Integer id){
        return professionDao.findMbtiByLike(id);
    }
    
}
