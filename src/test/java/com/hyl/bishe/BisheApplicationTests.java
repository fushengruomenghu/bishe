package com.hyl.bishe;

import com.hyl.bishe.controller.Impls.PersonControllerImpls;
import com.hyl.bishe.dao.PersonDao;
import com.hyl.bishe.entity.Person;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.PersonServiceImpls;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BisheApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("dsfffffffffffffffffffffff");
    }
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PersonServiceImpls userServiceImpls;
    @Autowired
    private PersonControllerImpls userControllerImpls;
    @Test
    void find() {
        System.out.println(personDao.findAll());
    }
    @Test
    void findALL() {
//        System.out.println(userServiceImpls.findAllUser());
    }
    @Test
    void RE() {
        userControllerImpls.register();
    }
    @Test
    void insert() {
//        Users users=new Users("1","1","1","1","1");
//        userDao.save(users);
//        userServiceImpls.InsertUser(users);
        findALL();
    }
    @Test
    void de(){
        Person person=personDao.findPersonByPhone("1");
//        userServiceImpls.deletePerson(person);

    }
}
