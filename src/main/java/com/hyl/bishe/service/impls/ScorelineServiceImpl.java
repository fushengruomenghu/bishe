package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.ScorelineDao;
import com.hyl.bishe.entity.Scoreline;
import com.hyl.bishe.service.ScorelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ScorelineServiceImpl implements ScorelineService {
    @Autowired
    private ScorelineDao scorelineDao;

    @Override
    public Page<Scoreline> findAll(Pageable page) {
        return scorelineDao.findAll(page);
    }
}
