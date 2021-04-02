package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CharacterDao extends JpaRepository<Character,Integer> {

    @Transactional
    @Modifying
    @Query(value = "update  `characteristic` set `province`=?2 where `college`=?1 and `province` is null " ,nativeQuery = true)
    void insertC(String name,String province);
    @Transactional
    @Modifying
    @Query(value = "select * from `characteristic` where `grade` between ?1 and ?2 and `location`=?3 and `leibie`=?4 ",nativeQuery = true)
    List<Character> findCharactersByGradeBetweenAndLocationAndLeibie(int start,int end,String location,String leibie);
    @Query("SELECT DISTINCT location from characteristic ")
    List<String> findDistinctLocation();


}
