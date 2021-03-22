package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessionDao extends JpaRepository<Profession,Integer> {
    Profession findProfessionByProname(String name);
}
