package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.SchoolInfoController;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.SchoolInfo;

import com.hyl.bishe.entity.University;
import com.hyl.bishe.service.impls.SchoolInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SchoolInfoControllerImpl implements SchoolInfoController {
    static Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);
    @Autowired
    private SchoolInfoServiceImpl schoolInfoService;

    @RequestMapping("/schoolInfo")
    public String ListSchool(HttpServletRequest request,HttpServletResponse response, Model model,Integer pageNum){
        List<String> locations= schoolInfoService.findLocation();
        List<String> types= schoolInfoService.findType();
        List<String> levels= schoolInfoService.findLevel();
        model.addAttribute("location",locations);
        model.addAttribute("type",types);
        model.addAttribute("level",levels);
        model.addAttribute("active","active");
        model.addAttribute("color","background-color: #ff8f8f");
        String location=request.getParameter("location");
        String education_level=request.getParameter("level");
        String type=request.getParameter("type");
        if (pageNum == null) {
            pageNum=1;
        }
        Sort sort=Sort.by(Sort.Direction.ASC,"location");
        Pageable pageable=PageRequest.of(pageNum-1,25,sort);
        Specification<SchoolInfo> specification=new Specification<SchoolInfo>() {
            @Override
            public Predicate toPredicate(Root<SchoolInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (location!=null) {
                    predicates.add(criteriaBuilder.equal(root.get("location"),location));
                }
                if (education_level!=null) {

                    predicates.add(criteriaBuilder.equal(root.get("education_level"),education_level));
                }
                if (type!=null) {

                    predicates.add(criteriaBuilder.equal(root.get("type"),type));
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };

        Page<SchoolInfo> list = schoolInfoService.findAll(specification,pageable);

        logger.info("pageNum==" + pageNum);
        model.addAttribute("schoolInfos",list);
        response.addHeader("x-frame-options","SAMEORIGIN");

        return "schoolInfo";
    }
    @RequestMapping("/GetSchoolInfo")
    public String  GetSchoolInfo(HttpServletRequest request, HttpSession session) {
        String schoolname=request.getParameter("name");
        System.out.println(schoolname);
        University university =schoolInfoService.findAllByName(schoolname);
        university.setSchWebsite(university.getSchWebsite().replaceAll("\\u00A0+", ""));
        university.setEnrollmentWebsite(university.getEnrollmentWebsite().replaceAll("\\u00A0+", ""));
        session.setAttribute("university", university);

        return "schooldetails";
    }
}
