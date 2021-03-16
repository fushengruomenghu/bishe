package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.PersonDao;
import com.hyl.bishe.entity.Person;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//@EnableJpaRepositories(basePackages = "com.hyl.bishe.dao")
@Service
public class PersonServiceImpls implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public Person InsertPerson(Person person) {
        return personDao.save(person);
    }

    @Override
    public List<Person> findAllPerson() {
        return personDao.findAll();
    }
    @Override
    public Person findByPhoneAndPassword(String phone, String password) {
        return personDao.findPersonByPhoneAndPassword(phone,password);
    }

    @Override
    public Person findByPhone(String phone) {
        return personDao.findPersonByPhone(phone);
    }

    @Override
    public void deleteByPhone(String phone) {
        personDao.deletePersonByPhone(phone);
    }

    @Override
    public void deletePerson(Person person) {
        personDao.delete(person);
    }
}
