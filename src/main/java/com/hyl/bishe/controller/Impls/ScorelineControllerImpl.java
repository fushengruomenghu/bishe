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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ScorelineControllerImpl implements ScorelineController {
    static Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);

    @Autowired
    private ScorelineServiceImpl scorelineService;

    @RequestMapping("/scoreline")
    public String ListSchool(HttpServletResponse response, Model model, Integer pageNum){

        List<String> province= scorelineService.findProvince();
        List<String> leibie= scorelineService.findleibie();
        List<String> pici= scorelineService.findpici();
        List<String> year= scorelineService.findYear();
        model.addAttribute("province",province);
        model.addAttribute("leibie",leibie);
        model.addAttribute("year",year);
        model.addAttribute("pici",pici);

        if (pageNum == null) {
            pageNum=1;
        }
        Sort sort=Sort.by(Sort.Direction.DESC,"location","year");
        Pageable pageable= PageRequest.of(pageNum-1,25,sort);
        Page<Scoreline> list = scorelineService.findAll(pageable);

        logger.info("pageNum==" + pageNum);
        model.addAttribute("score_line",list);
        response.addHeader("x-frame-options","SAMEORIGIN");
        return "score_line";
    }
}
