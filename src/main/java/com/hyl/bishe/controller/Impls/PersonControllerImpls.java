package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.PersonController;
import com.hyl.bishe.entity.Person;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.CharacterServiceImpl;
import com.hyl.bishe.service.impls.PersonServiceImpls;
import com.hyl.bishe.service.impls.ScorelineServiceImpl;
import com.hyl.bishe.service.impls.UsersServiceImpl;
import com.hyl.bishe.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class PersonControllerImpls implements PersonController {
    private final static Logger logger = LoggerFactory.getLogger(PersonControllerImpls.class);

    @Autowired
    private PersonServiceImpls personServiceImpls;
    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private CharacterServiceImpl characterService;
    @Autowired
    private ScorelineServiceImpl scorelineService;


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
                Users user=new Users();
                user.setPhone(person1.getPhone());
                usersService.sava(user);
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
    public String doLogin(HttpServletRequest request, HttpSession session, Model model){
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String verifyInput=request.getParameter("verify_input");
        System.out.println(phone+"+++++++++++++++"+password);
        Person person=personServiceImpls.findByPhoneAndPassword(phone,password);
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");

        if (random.equals(verifyInput)) {
            if (person != null) {
                Users users=usersService.findUserByPhone(person.getPhone());
                List<String> location= characterService.findLocation();
                List<String> leibie= scorelineService.findleibie();
                session.setAttribute("location",location);
                session.setAttribute("leibie",leibie);
                session.setAttribute("Users",users);
                System.out.println("登陆成功");
                return "redirect:/schoolInfo";
            }else {
                System.out.println("用户名或密码错误");
                return "login";
            }
        }else {
            System.out.println("验证码错误");
            return "login";
        }
    }

    @RequestMapping("/forgetpassword")
    @Override
    public String forgetPassword() {
        return "forgetpassword";
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping("/forgetPassword")
    public String updatePassword(HttpServletRequest request){
        String password1 = request.getParameter("pw1");
        String password2 = request.getParameter("pw2");
        String phone = request.getParameter("phone");
        Person person1=personServiceImpls.findByPhone(phone);

        if ( person1!=null) {
            if (password1.equals(password2)) {
                    personServiceImpls.updatepassword(password1,phone);
                    System.out.println("密码修改成功");
                    return "login";
            }else {
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
        return "schooldetails";
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String LogOut(HttpSession session){
        session.removeAttribute("Users");
        return "redirect:/";
    }

    /**
     * 验证码
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {

        try {

            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");

            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);

            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();


            randomValidateCode.getRandomCode(request, response);//输出验证码图片方法

        } catch (Exception e) {

            logger.error("获取验证码失败>>>>   ", e);

        }

    }
    @ResponseBody
    @RequestMapping(value = "/checkVerify")
    public String checkVerify(@RequestParam(value = "verifyInput") String verifyInput, HttpSession session) {
        try {

            //从session中获取随机数
            String inputStr = verifyInput;
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null || "".equals(random) || !random.equalsIgnoreCase(inputStr)) {
                return "fail";
            } else {
                return "success";
            }

        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            return "fail";
        }
    }
}
