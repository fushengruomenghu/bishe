package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordDao extends JpaRepository<Record,Integer> {
}
