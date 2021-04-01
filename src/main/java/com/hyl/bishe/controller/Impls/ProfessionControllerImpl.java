package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.ProfessionController;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.service.impls.ProfessionServiceImpl;
import com.hyl.bishe.service.impls.SchoolInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProfessionControllerImpl implements ProfessionController {
    static Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);

    @Autowired
    private ProfessionServiceImpl professionService;

    @RequestMapping("/professioninfo")
    public String ListSchool(HttpServletRequest request,HttpServletResponse response, Model model, Integer pageNum){

        List<String> benke= professionService.findMenglei("本科专业");
        List<String> zuanke= professionService.findMenglei("高职专科专业");
        model.addAttribute("bk",benke);
        model.addAttribute("zk",zuanke);
        String degree_category=request.getParameter("degree_category");
        String disciplines=request.getParameter("disciplines");
        if (pageNum == null) {
            pageNum=1;
        }
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        Pageable pageable= PageRequest.of(pageNum-1,25,sort);
        Page<Profession> professions=null;
        if (degree_category == null&&disciplines==null) {
             professions= professionService.findAll(pageable);
        }

        logger.info("pageNum==" + pageNum);
        model.addAttribute("profession",professions);
        response.addHeader("x-frame-options","SAMEORIGIN");
        return "profession";
    }


    @PostMapping("/a1")
    public String findByCondition(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "25") Integer size,
                                  @RequestParam(value = "disciplines", defaultValue = "男") String disciplines,
                                  @RequestParam(value = "pro_category") String pro_category){
        List<Profession> professionsList = professionService.findByCondition(page, size, disciplines, pro_category).getContent();
        Map<String, Object> map = new HashMap();
        map.put("num", professionsList.size());
        map.put("listData", professionsList);
        return "list";
    }

    @RequestMapping("/GetProfession")
    public String  GetSchoolInfo(HttpServletRequest request, HttpSession session) {
        String proname=request.getParameter("name");
        Profession profession=professionService.findbyName(proname);
        session.setAttribute("profession",profession);
        return "professionDetails";
    }
}
