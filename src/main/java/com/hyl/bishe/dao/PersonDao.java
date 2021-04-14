package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Person;
import com.hyl.bishe.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {
    Person findPersonByPhoneAndPassword(String phone,String password);
    Person findPersonByPhone(String phone);
    void deletePersonByPhone(String phone);

    @Transactional
    @Modifying
    @Query(value = "update person set password=?1 where phone=?2",nativeQuery = true)
    void updatepassword(String password,String phone);
}
