package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.PersonController;
import com.hyl.bishe.controller.UsersController;
import com.hyl.bishe.entity.Person;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.PersonServiceImpls;
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

//    @RequestMapping("/details")
    @Override
    public String showUsersList() {
        return "";
    }
    public String UsersList(List<Users> usersList){

        return "";
    }
}
