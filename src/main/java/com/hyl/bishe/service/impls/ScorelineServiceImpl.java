package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.ScorelineDao;
import com.hyl.bishe.entity.Scoreline;
import com.hyl.bishe.service.ScorelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScorelineServiceImpl implements ScorelineService {
    @Autowired
    private ScorelineDao scorelineDao;

    @Override
    public Page<Scoreline> findAll(Pageable page) {
        return scorelineDao.findAll(page);
    }

    @Override
    public List<String> findProvince() {
        return scorelineDao.findDistinctLocation();
    }

    @Override
    public List<String> findYear() {
        return scorelineDao.findDistinctYear();
    }

    @Override
    public List<String> findleibie() {
        return scorelineDao.findDistinctLeibie();
    }

    @Override
    public List<String> findpici() {
        return scorelineDao.findDistinctPici();
    }
}
