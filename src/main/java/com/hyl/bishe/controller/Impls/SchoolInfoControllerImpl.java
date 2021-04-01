package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.SchoolInfoController;
import com.hyl.bishe.entity.SchoolInfo;

import com.hyl.bishe.entity.University;
import com.hyl.bishe.service.impls.SchoolInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SchoolInfoControllerImpl implements SchoolInfoController {
    static Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);
    @Autowired
    private SchoolInfoServiceImpl schoolInfoService;

    @RequestMapping("/schoolInfo")
    public String ListSchool(HttpServletResponse response, Model model,Integer pageNum){
        List<String> location= schoolInfoService.findLocation();
        List<String> type= schoolInfoService.findType();
        List<String> level= schoolInfoService.findLevel();
        model.addAttribute("location",location);
        model.addAttribute("type",type);
        model.addAttribute("level",level);

        if (pageNum == null) {
            pageNum=1;
        }
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        Pageable pageable=PageRequest.of(pageNum-1,25,sort);

        Page<SchoolInfo> list = schoolInfoService.findAll(pageable);

        logger.info("pageNum==" + pageNum);
        model.addAttribute("schoolInfos",list);
        response.addHeader("x-frame-options","SAMEORIGIN");

        return "schoolInfo";
    }
    @RequestMapping("/GetSchoolInfo")
    public String  GetSchoolInfo(HttpServletRequest request, HttpSession session) {
        String schoolname=request.getParameter("name");
        University university =schoolInfoService.findAllByName(schoolname);

        session.setAttribute("university", university);
        return "schooldetails";
    }
}
