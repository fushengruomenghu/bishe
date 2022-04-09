package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Character;
import com.hyl.bishe.entity.CharacterAndSchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CharacterDao extends JpaRepository<Character,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update  `characteristic` set `province`=?2 where `college`=?1 and `province` is null ", nativeQuery = true)
    void insertC(String name, String province);
    @Transactional
    @Modifying
    @Query(value = "select * from `characteristic` where `grade` between ?1 and ?2 and `location`=?3 and `leibie`=?4 ", nativeQuery = true)
    List<Character> findCharactersByGradeBetweenAndLocationAndLeibie(int start, int end, String location, String leibie);
    @Transactional
    @Modifying
    @Query(value = "select * from `characteristic` where `grade` between ?1 and ?2 and `location`=?3 and `majorname`=?4 ", nativeQuery = true)
    List<Character> findCharactersByGradeBetweenAndLocationAndMajorname(int start, int end, String location, String major);
    @Transactional
    @Modifying
    @Query(value = "select * from characteristic where grade between ?1 and ?2 and province=?5 and  location=?3 and leibie=?4 ",nativeQuery = true)
    List<Character> findCharacterByGradeBetweenAndLocationAndLeibie(int start, int end, String location, String leibie,String province);
    @Query("SELECT DISTINCT location from characteristic ")
    List<String> findDistinctLocation();
    List<Character> findCharacterByCollege(String college);
    List<Character> findCharacterByPidIsNull();
    @Transactional
    @Modifying
    @Query(value = "update  `characteristic` set `pid`=?1 where `majorname`=?2 and `pid` is null ", nativeQuery = true)
    void updatePid(Integer pid, String majorname);
}
