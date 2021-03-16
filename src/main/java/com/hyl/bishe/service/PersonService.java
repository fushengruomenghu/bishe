package com.hyl.bishe.service;

import com.hyl.bishe.entity.Person;
import com.hyl.bishe.entity.Users;

import java.util.List;


public interface PersonService {
    Person InsertPerson(Person person);
    List<Person>  findAllPerson();
    Person findByPhoneAndPassword(String phone,String password);
    Person findByPhone(String phone);
    void deleteByPhone(String phone);
    void deletePerson(Person person);
}
