package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.UsersController;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsersControllerImpls implements UsersController {

    @Autowired
    private UsersServiceImpl usersService;


    @RequestMapping("/userinfo")
    @Override
    public String showUsersInfo() {
        return "userinfo";
    }

    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(@RequestParam(value = "u_name") String username,@RequestParam(value = "u_sex") String sex, @RequestParam(value = "u_phone") String phone,
                                 @RequestParam(value = "u_location") String location, @RequestParam(value = "u_pici") String pici,@RequestParam(value = "u_grade") String grade, @RequestParam(value = "u_leibie") String leibie, @RequestParam(value = "u_hobby") String hobby
            , HttpSession session){

        System.out.println(username+","+sex+","+phone+","+location+","+grade+","+leibie+","+pici+","+hobby);
        Users users=usersService.findUserByPhone(phone);
        Integer id=users.getId();
        Users user=new Users(id,username,sex,phone,location,grade,leibie,pici,hobby);
        session.setAttribute("Users",user);
        usersService.updateUserById(user,id);
        return "添加成功";
    }
}
