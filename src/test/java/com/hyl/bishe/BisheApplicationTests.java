package com.hyl.bishe;

import com.hyl.bishe.controller.Impls.PersonControllerImpls;
import com.hyl.bishe.dao.*;
import com.hyl.bishe.entity.Character;
import com.hyl.bishe.entity.Person;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.University;
import com.hyl.bishe.service.impls.PersonServiceImpls;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class BisheApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("dsfffffffffffffffffffffff");
    }
    @Autowired
    private PersonDao personDao;
    @Autowired
    private UniversityDao universityDao;
    @Autowired
    private ScorelineDao scorelineDao;
    @Autowired
    private ProfessionDao professionDao;
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
        List<String> list=new LinkedList<>();
        list= scorelineDao.findDistinctPici();
        for (String province:list){
            System.out.println(province);
        }
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
//        findALL();
        List<String> disciplines = professionDao.findDistinctDisciplines("高职专科专业");
        System.out.println(disciplines);
    }
    @Test
    void de(){
        Person person=personDao.findPersonByPhone("18779933002");
        System.out.println(person.getPhone());
//        userServiceImpls.deletePerson(person);

    }
    @Test
    void findbyname(){
       University university=universityDao.findAllByName("北京大学");
        System.out.println(university.getSchWebsite());
    }

    @Autowired
    private CharacterDao characterDao;

    @Autowired
    private SchoolInfoDao schoolInfoDao;

    @Test
    void findall(){

//      List<Character> characterList=  characterDao.findCharactersByGradeBetweenAndLocationAndLeibie(560,600,"北京","理科");

        List<String> list=professionDao.findProNameByDegreeCategory("%文学%","%语言%","%新闻%");
        System.out.println(list);
    }

    @Test
    void Insert(){

        List<Character> characters=characterDao.findAll();
        List<SchoolInfo> schoolInfos= schoolInfoDao.findAll();
        for(Character character:characters){
            for(SchoolInfo schoolInfo:schoolInfos){
                if (schoolInfo.getSchname().equals(character.getCollege())) {
                      if(character.getProvince()==null){

                        characterDao.insertC(schoolInfo.getSchname(),schoolInfo.getLocation());
                      }
                }
            }
        }
    }
}
