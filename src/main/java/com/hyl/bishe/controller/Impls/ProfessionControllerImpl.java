package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.ProfessionController;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.service.impls.ProfessionServiceImpl;
import com.hyl.bishe.service.impls.SchoolInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        model.addAttribute("active2","active");
        model.addAttribute("color2","background-color: #ff8f8f");
        String pro_category=request.getParameter("pro_category");
        String disciplines=request.getParameter("disciplines");
//        pro_category="本科专业";
//        disciplines="理学";
        if (pageNum == null) {
            pageNum=1;
        }
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        Pageable pageable= PageRequest.of(pageNum-1,25,sort);
        Specification<Profession> specification=new Specification<Profession>() {
            @Override
            public Predicate toPredicate(Root<Profession> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (disciplines!=null&&!disciplines.equals("null")) {
                    predicates.add(criteriaBuilder.equal(root.get("disciplines"),disciplines));
                }
                if (pro_category!=null&&!pro_category.equals("null")) {

                    predicates.add(criteriaBuilder.equal(root.get("pro_category"),pro_category));
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Page<Profession> professions = professionService.findAll(specification,pageable);
//        professions.getContent().remove()



        logger.info("pageNum==" + pageNum);
        model.addAttribute("profession",professions);

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
