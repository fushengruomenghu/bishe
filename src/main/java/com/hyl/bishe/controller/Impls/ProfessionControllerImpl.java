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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ProfessionControllerImpl implements ProfessionController {
    static Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);

    @Autowired
    private ProfessionServiceImpl professionService;

    @RequestMapping("/professioninfo")
    public String ListSchool(HttpServletResponse response, Model model, Integer pageNum){
        if (pageNum == null) {
            pageNum=1;
        }
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        Pageable pageable= PageRequest.of(pageNum-1,25,sort);

        Page<Profession> profession = professionService.findAll(pageable);

        logger.info("pageNum==" + pageNum);
        model.addAttribute("profession",profession);
        response.addHeader("x-frame-options","SAMEORIGIN");
        return "profession";
    }


    @RequestMapping("/GetProfession")
    public String  GetSchoolInfo(HttpServletRequest request, HttpSession session) {
        String proname=request.getParameter("name");
        Profession profession=professionService.findbyName(proname);
        session.setAttribute("profession",profession);
        return "professionDetails";
    }
}
