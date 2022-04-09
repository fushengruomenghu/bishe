package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Profession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ProfessionDao extends JpaRepository<Profession,Integer> , JpaSpecificationExecutor<Profession> {
    Profession findProfessionByProname(String name);

    int countById(Integer id);
    @Query(value = "select mbti from pro_info where proname=?1")
    String findMbtiByName(String name);
    @Query(value = "select holland from pro_info where proname=?1")
    String findHollandByName(String name);

    @Query(value = "SELECT DISTINCT disciplines from pro_info where pro_category=?1")
    List<String> findDistinctDisciplines(String degree_category);

    Page<Profession> findAll(Specification<Profession> specification, Pageable pageable);
    @Query(value = "select proname from pro_info where degree_category like ?1 or degree_category like ?2 or degree_category like ?3")
    List<String> findProNameByDegreeCategory(String name1,String name2,String name3);

    @Query(value = "select t from pro_info t where t.mbti like %?1% and t.holland like %?2%")
    List<Profession> findByLike(String mbti,String holland);

    @Query(value = "select t from pro_info t where t.mbti like %?1% ")
    List<Profession> findByLike(String mbti);

    @Query(value = "select mbti from pro_info  where id =?1 ")
    String findMbtiByLike(Integer id);
//    List<Profession> findProfessionByDegree_category(String major);
}
