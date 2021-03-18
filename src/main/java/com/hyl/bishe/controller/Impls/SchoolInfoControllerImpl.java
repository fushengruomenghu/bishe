package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.controller.SchoolInfoController;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.SchoolInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class SchoolInfoControllerImpl implements SchoolInfoController {
    @Autowired
    private SchoolInfoServiceImpl schoolInfoService;

    @RequestMapping("/schoolInfo")
    public String ListSchool(Map<String,Object> map,
                             @RequestParam(value = "pageNum", defaultValue = "0")     int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "2") int pageSize){
        Page<SchoolInfo> schoolInfos=schoolInfoService.getSchoolInfoList(pageNum,pageSize);
        Iterator<SchoolInfo> schoolInfoIterator = schoolInfos.iterator();
//        List<SchoolInfo> schoolInfo=schoolInfoService.findAllSchoolInfo();

        while (schoolInfoIterator.hasNext()){

            System.out.println(schoolInfoIterator.next().toString());
        }

        map.put("schoolInfos",schoolInfos);
        return "schoolInfo";
    }

}
