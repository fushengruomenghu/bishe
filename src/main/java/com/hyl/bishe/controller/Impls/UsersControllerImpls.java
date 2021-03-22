package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.UsersController;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(HttpServletRequest request, HttpSession session){
        String username=request.getParameter("u_name");
        String sex=request.getParameter("u_sex");
        String phone=request.getParameter("u_phone");
        String location=request.getParameter("u_location");
        String pici=request.getParameter("u_pici");
        String grade=request.getParameter("u_grade");
        String leibie=request.getParameter("u_leibie");
        String hobby=request.getParameter("u_hobby");
        Users users=usersService.findUserByPhone(phone);
        Integer id=users.getId();

        usersService.deleteUser(users);
        sex=sex.equals("男")?"0":"1";
        leibie=leibie.equals("文科")?"0":"1";
        pici=pici.equals("本科第一批次")?"0":"1";
        Users user=new Users(id,username,sex,phone,location,grade,leibie,pici,hobby);
        session.setAttribute("Users",user);
        usersService.sava(user);
        return "redirect:/schoolInfo";
    }

}
