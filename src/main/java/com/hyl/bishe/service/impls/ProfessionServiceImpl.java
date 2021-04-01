package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.ProfessionDao;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionDao professionDao;
    @Override
    public Page<Profession> findAll(Pageable page) {
        return professionDao.findAll(page);
    }

    public List<Profession> findAll() {
        return professionDao.findAll();
    }

    @Override
    public Page<Profession> findByCondition(Integer page, Integer size, String disciplines, String pro_category) {
        Pageable pageable = PageRequest.of(page, size);

        return professionDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates=new ArrayList<>();
            if (disciplines!=null) {
                predicates.add(criteriaBuilder.equal(root.get("disciplines"),disciplines));
            }
            if (pro_category!=null) {
                predicates.add(criteriaBuilder.equal(root.get("pro_category"),pro_category));
            }
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        },pageable);
    }

    @Override
    public List<String> findMenglei(String menglei) {
        return professionDao.findDistinctDisciplines(menglei);
    }

    public Profession findbyName(String name){
        return professionDao.findProfessionByProname(name);
    }

    
}
