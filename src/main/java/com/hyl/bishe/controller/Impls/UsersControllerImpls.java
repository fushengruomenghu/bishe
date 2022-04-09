package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.UsersController;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.PersonServiceImpls;
import com.hyl.bishe.service.impls.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                                 @RequestParam(value = "u_location") String location, @RequestParam(value = "u_secret") String secret,@RequestParam(value = "u_grade") String grade, @RequestParam(value = "u_leibie") String leibie, @RequestParam(value = "u_major") String major
            , HttpSession session){

        System.out.println(username+","+sex+","+phone+","+location+","+grade+","+leibie+","+secret+","+major);
        Users users=usersService.findUserByPhone(phone);
        Integer id=users.getId();
        Users user=new Users(id,username, users.getPassword(), sex,phone,location,grade,leibie,major,users.getUsersrole(),secret);
        session.setAttribute("Users",user);
        usersService.updateUserById(user,id);
        return "添加成功";
    }
    @RequestMapping(value = "/UserInfo")
    public String ListUsers(Model model){
        List<Users> usersList=usersService.findAllUsers();
        model.addAttribute("userlist",usersList);
        return "userlist";
    }
    @ResponseBody
    @RequestMapping(value = "/DeleteUserInfo")
    public String DeleUsers(@RequestParam(value = "phone") String phone){
        Users users=usersService.findUserByPhone(phone);
        System.out.println(users);
        usersService.deleteUser(users);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/UpUserInfo")
    public String UpUsers(@RequestParam(value = "id") String id,@RequestParam(value = "username") String username,@RequestParam(value = "sex") String sex,@RequestParam(value = "phone") String phone,
                           @RequestParam(value = "location") String location,@RequestParam(value = "grade") String grade,
                           @RequestParam(value = "leibie") String leibie,@RequestParam(value = "major") String major,@RequestParam(value = "usersrole") String usersrole, HttpSession session){

        System.out.println(id+","+username+","+sex+","+phone+","+location+","+grade+","+leibie+","+major+","+usersrole);
        int id1=Integer.parseInt(id);
        Users users=usersService.findUserById(id1);
        Users user=new Users(id1,username,users.getPassword(),sex,phone,location,grade,leibie,major,usersrole,users.getSecret());
        session.setAttribute("list_user",user);
        usersService.updateUserById(user,id1);
        return "添加成功";
    }
    @RequestMapping(value = "/EditUser")
    public String EditUser(HttpServletRequest request,Model model){
        String id=request.getParameter("id");
        Users users=usersService.findUserById(Integer.parseInt(id));
        model.addAttribute("list_user",users);
        return "edituser";
    }
}
