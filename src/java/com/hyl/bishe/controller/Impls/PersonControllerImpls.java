package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.PersonController;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.CharacterServiceImpl;
import com.hyl.bishe.service.impls.ScorelineServiceImpl;
import com.hyl.bishe.service.impls.UsersServiceImpl;
import com.hyl.bishe.utils.MD5Utils;
import com.hyl.bishe.utils.RandomValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@Controller
public class PersonControllerImpls implements PersonController {
    private final static Logger logger = LoggerFactory.getLogger(PersonControllerImpls.class);

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
    public String doRegister(HttpServletRequest request,Model model) {
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String phone = request.getParameter("phone");
        String secret=request.getParameter("secret");
        String shengfen=request.getParameter("shengfen");

        List<Users> usersList=usersService.findAllUsers();
        Users users=new Users();
        Boolean flag = false;

        if (usersList.size()==0) {
            flag = true;
        }else {
            for (Users users1 : usersList) {
                if (users1.getPhone().equals(phone)) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }
        if (flag) {
            if (password1.equals(password2)) {
                users.setPhone(phone);
                users.setPassword(MD5Utils.inputPassToFormPass(password1));
                users.setUsersrole(shengfen);

                users.setSecret(secret);
                usersService.sava(users);
                model.addAttribute("registerMsg","注册成功");
                System.out.println("注册成功");
                return "login";
            } else {
                System.out.println("密码不一致");
                return "register";
            }
        } else {
            System.out.println("手机号已存在");
            System.out.println(usersList.toArray());
            return "register";
        }
    }

    @Override
    @RequestMapping("/")
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/doLogin")
    public String doLogin(HttpServletRequest request, HttpSession session, Model model){
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String verifyInput=request.getParameter("verify_input");
        System.out.println(phone+"+++++++++++++++"+password);
        Users users=usersService.findUsersByPhoneAndPassword(phone,MD5Utils.inputPassToFormPass(password));
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");

        if (random.equalsIgnoreCase(verifyInput)) {
            if (users != null) {
                List<String> location= characterService.findLocation();
                List<String> leibie= scorelineService.findleibie();
                session.setAttribute("location",location);
                session.setAttribute("leibie",leibie);
                session.setAttribute("Users",users);
                session.setAttribute("Usersrole",users.getUsersrole());
                model.addAttribute("loginMsg","登陆成功");
                return "redirect:/schoolInfo";
            }else {
                System.out.println("用户名或密码错误");
                model.addAttribute("loginError","用户名或密码错误");
                return "login";
            }
        }else {
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
        String secret=request.getParameter("secret");

        Users users=usersService.findUserByPhone(phone);

        if ( users!=null) {
            if (password1.equals(password2)&&secret.equals(users.getSecret())) {
                    usersService.updatepassword(MD5Utils.inputPassToFormPass(password1),phone);
                    System.out.println("密码修改成功");
                    return "login";
            }else {
                System.out.println("密码不一致或密保错误");
                return "register";
            }
        } else {
            System.out.println("手机号不存在");
            return "register";
        }
    }

//    @RequestMapping("/details")
//    public String GetAll(Map<String,Object> map){
//       List<Person> personList= personServiceImpls.findAllPerson();
//       map.put("personList",personList);
//        return "schooldetails";
//    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String LogOut(HttpSession session){
        session.removeAttribute("Users");
        Enumeration em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement().toString());
        }
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
