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
    @Query(value = "select * from characteristic where grade between ?1 and ?2 and province=?5 and  location=?3 and leibie=?4 ",nativeQuery = true)
    List<Character> findCharacterByGradeBetweenAndLocationAndLeibie(int start, int end, String location, String leibie,String province);

    @Query("SELECT DISTINCT location from characteristic ")
    List<String> findDistinctLocation();

    //    @Query("select s from Student s where s.clazz.name=?1")
//    List<Student> findStudentsByClazzname(String clazzName);
//    @Query(value = "select new com.hyl.bishe.entity.CharacterAndSchoolInfo( " +
//            "ch.province,ch.majorname,ch.college,ch.location,ch.leibie,ch.grade,ch.batch,sc.comprehensive,sc.environment,sc.life )" +
//            "from characteristic ch left join schoolinfo sc " +
//            "on ch.college=sc.schname and ch.grade  " +
//            "between ?1 and ?2 and  ch.location =?3 and  ch.leibie =?4 group by ch.id", nativeQuery = false)
//    List<CharacterAndSchoolInfo> findCharacterAndSchoolInfo(String start, String end, String location, String leibie);
//
//    @Query(value = "select new com.hyl.bishe.entity.CharacterAndSchoolInfo( " +
//            "ch.province,ch.majorname,ch.college,ch.location,ch.leibie,ch.grade,ch.batch,sc.comprehensive,sc.environment,sc.life )" +
//            "from characteristic ch left join schoolinfo sc" +
//            " on ch.college=sc.schname and ch.grade between ?1 and ?2 group by ch.id", nativeQuery = true)
//    List<CharacterAndSchoolInfo> findCharacterAndSchool(String grade1, String grade2);

    List<Character> findCharacterByCollege(String college);
}

