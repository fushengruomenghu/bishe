package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.ProfessionDao;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionDao professionDao;
    @Override
    public Page<Profession> findAll(Pageable page) {
        return professionDao.findAll(page);
    }
    public Profession findbyName(String name){
        return professionDao.findProfessionByProname(name);
    }

}
