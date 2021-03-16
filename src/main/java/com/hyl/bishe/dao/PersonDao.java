package com.hyl.bishe.dao;

import com.hyl.bishe.entity.Person;
import com.hyl.bishe.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {
    Person findPersonByPhoneAndPassword(String phone,String password);
    Person findPersonByPhone(String phone);
    void deletePersonByPhone(String phone);
}
