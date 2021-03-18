package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Scoreline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScorelineDao extends JpaRepository<Scoreline,Integer> {
}
