package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.SchoolInfoController;
import com.hyl.bishe.dao.SchoolInfoDao;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.SchoolInfoServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class SchoolInfoControllerImpl implements SchoolInfoController {
    static Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);
    @Autowired
    private SchoolInfoServiceImpl schoolInfoService;

    @RequestMapping("/schoolInfo")
    public String ListSchool(HttpServletResponse response, Model model,Integer pageNum){
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

}
