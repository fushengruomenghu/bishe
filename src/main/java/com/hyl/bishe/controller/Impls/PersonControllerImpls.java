package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.PersonController;
import com.hyl.bishe.entity.Person;
import com.hyl.bishe.service.impls.PersonServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class PersonControllerImpls implements PersonController {

    @Autowired
    private PersonServiceImpls personServiceImpls;

    @Override
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/doRegister")
    public String doRegister(HttpServletRequest request) {
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String phone = request.getParameter("phone");

        List<Person> personList=personServiceImpls.findAllPerson();
        Person person1=new Person();
        Boolean flag = false;

        for (Person person : personList) {
            if (person.getPhone().equals(phone)) {
                flag = false;
            } else {
                flag = true;
            }
        }
        if (flag) {
            if (password1.equals(password2)) {
                person1.setPhone(phone);
                person1.setPassword(password1);
                personServiceImpls.InsertPerson(person1);
                System.out.println("注册成功");
                return "login";
            } else {
                System.out.println("密码不一致");
                return "register";
            }
        } else {
            System.out.println("手机号已存在");
            return "register";
        }
    }

    @Override
    @RequestMapping("/")
    public String login() {
        return "login";
    }
    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request){
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        System.out.println(phone+"+++++++++++++++"+password);
        Person person=personServiceImpls.findByPhoneAndPassword(phone,password);

        if (person != null) {
            System.out.println("登陆成功");
            return "list";
        }else {
            System.out.println("用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/forgetpassword")
    @Override
    public String forgetPassword() {
        return "forgetpassword";
    }
    @RequestMapping("/forgetPassword")
    public String updatePassword(HttpServletRequest request){
        String password1 = request.getParameter("pw1");
        String password2 = request.getParameter("pw2");
        String phone = request.getParameter("phone");
        Person person=new Person();
        Person person1=personServiceImpls.findByPhone(phone);

        if ( person1!=null) {
            if (password1.equals(password2)) {
                Integer id=person1.getId();
                personServiceImpls.deletePerson(person1);
                if (personServiceImpls.findByPhone(phone) == null) {
                    person.setId(id);
                    person.setPhone(phone);
                    person.setPassword(password1);
                    personServiceImpls.InsertPerson(person);
                    System.out.println("密码修改成功");
                    return "login";
                }
                return "forgetpassword";
            } else {
                System.out.println("密码不一致");
                return "register";
            }
        } else {
            System.out.println("手机号已存在");
            return "register";
        }
    }

    @RequestMapping("/details")
    public String GetAll(Map<String,Object> map){
       List<Person> personList= personServiceImpls.findAllPerson();
       map.put("personList",personList);
        return "details";
    }
}
