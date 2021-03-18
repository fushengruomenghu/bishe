package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.UsersController;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersControllerImpls implements UsersController {

    @Autowired
    private UsersServiceImpl usersService;


    @Override
    public String showUsersInfo() {
        Users users=usersService.findUserByPhone("");
        return "";
    }
    @RequestMapping("updateUserInfo")
    public String updateUserInfo(HttpServletRequest request){
        String username=request.getParameter("u_name");
        String sex=request.getParameter("u_sex");
        String phone=request.getParameter("u_phone");
        String location=request.getParameter("u_location");
        String pici=request.getParameter("u_pici");
        String grade=request.getParameter("u_grade");
        String leibie=request.getParameter("u_leibie");
        String hobby=request.getParameter("u_hobby");
        Users users=new Users();
        sex=sex.equals("男")?"0":"女";
        leibie=leibie.equals("文科")?"0":"1";
        pici=pici.equals("本科第一批次")?"0":"1";
        users.setUsername(username);
        users.setSex(sex);
        users.setPhone(phone);
        users.setLocation(location);
        users.setPici(pici);
        users.setGrade(grade);
        users.setHobby(hobby);
        users.setLeibie(leibie);
        usersService.sava(users);
        return "/schoolInfo";
    }

}
