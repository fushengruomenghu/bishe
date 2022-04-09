package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.ScorelineController;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Scoreline;
import com.hyl.bishe.service.impls.SchoolInfoServiceImpl;
import com.hyl.bishe.service.impls.ScorelineServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class ScorelineControllerImpl implements ScorelineController {
    static Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);

    @Autowired
    private ScorelineServiceImpl scorelineService;

    @RequestMapping("/scoreline")
    public String ListSchool(HttpServletRequest request, HttpServletResponse response, Model model, Integer pageNum){

        List<String> provinces= scorelineService.findProvince();
        List<String> leibies= scorelineService.findleibie();
        List<String> picis= scorelineService.findpici();
        List<String> years= scorelineService.findYear();
        model.addAttribute("province",provinces);
        model.addAttribute("leibie",leibies);
        model.addAttribute("year",years);
        model.addAttribute("pici",picis);
        model.addAttribute("active1","active");
        model.addAttribute("color1","background-color: #ff8f8f");
        String province=request.getParameter("location");
        String leibie=request.getParameter("leibie");
        String pici=request.getParameter("pici");
        String year=request.getParameter("year");

        if (pageNum == null) {
            pageNum=1;
        }
        Sort sort=Sort.by(Sort.Direction.DESC,"location","year");
        Pageable pageable= PageRequest.of(pageNum-1,25,sort);

        Specification<Scoreline> specification=new Specification<Scoreline>() {
            @Override
            public Predicate toPredicate(Root<Scoreline> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (province!=null&&!province.equals("")) {
                    predicates.add(criteriaBuilder.equal(root.get("location"),province));
                    model.addAttribute("prov",province);
                }
                if (leibie!=null&&!leibie.equals("")) {
                    predicates.add(criteriaBuilder.equal(root.get("leibie"),leibie));
                    model.addAttribute("lei",leibie);
                }
                if (pici!=null&&!pici.equals("")) {
                    predicates.add(criteriaBuilder.equal(root.get("pici"),pici));
                    model.addAttribute("pic",pici);
                }
                if (year!=null&&!year.equals("")) {
                    predicates.add(criteriaBuilder.equal(root.get("year"),year));
                    model.addAttribute("yr",year);
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };

        Page<Scoreline> list = scorelineService.findAll(specification,pageable);

        logger.info("pageNum==" + pageNum);
        model.addAttribute("score_line",list);
        response.addHeader("x-frame-options","SAMEORIGIN");
        return "score_line1";
    }
}
