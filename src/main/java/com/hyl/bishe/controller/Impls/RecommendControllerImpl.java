package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.entity.Character;
import com.hyl.bishe.entity.Profession;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.service.impls.CharacterServiceImpl;
import com.hyl.bishe.service.impls.ProfessionServiceImpl;
import com.hyl.bishe.service.impls.SchoolInfoServiceImpl;
import com.hyl.bishe.service.impls.ScorelineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecommendControllerImpl {

    @Autowired
    private ScorelineServiceImpl scorelineService;
    @Autowired
    private CharacterServiceImpl characterService;
    @Autowired
    private SchoolInfoServiceImpl schoolInfoService;
    @Autowired
    private ProfessionServiceImpl professionService;

    @RequestMapping("/Recommend")
    public String  Recommend(Model model){
        List<String> province= scorelineService.findProvince();
        List<String> location= characterService.findLocation();
        List<String> pici= scorelineService.findpici();
        model.addAttribute("province",province);
        model.addAttribute("pici",pici);
        model.addAttribute("location",location);
        return "recommend";
    }


    @ResponseBody
    @RequestMapping("/doRecommend")
    public List<Character> doRecommend(@RequestParam(value = "location") String location,@RequestParam(value = "province") String province,
                             @RequestParam(value = "leibie",defaultValue = "理科") String leibie, @RequestParam(value = "grade",defaultValue = "600") String grade,
                             @RequestParam(value = "course1") String course1, @RequestParam(value = "course2") String course2,Model model){
        int changji=Integer.parseInt(grade);

        Profession profession=professionService.findbyName("");
        SchoolInfo schoolInfo=schoolInfoService.findByName("");
        List<Profession> professionList= new ArrayList<>();
        List<SchoolInfo> schoolInfoList= new ArrayList<>();
        List<Character> characterList=characterService.findByGradeBetween(changji-5,changji+5,location,leibie);

        if (province!=null) {
            characterList.removeIf(character -> !character.getProvince().equals(province));
        }

          //        List<String> list=new ArrayList<>();
//        list.add(province);
//        list.add(course1);
//        list.add(course2);
//        list.add(major);
//        for (int i = 0; i < list.size(); i++) {
//            characterList=GetByAttribute(characterList,list,i);
//        }
//        model.addAttribute("characterList",characterList);
        return characterList;
    }
//    public List<Character> GetByAttribute(List<Character> characterList,List<String> attribute,int i){
//        switch (i){
//            case 0:
//                if (attribute.get(i)!=null) {
//                    characterList.removeIf(character -> character.getProvince().equals(attribute.get(i)));
//                }
//                break;
//            case 1:
//                if (attribute.get(i).equals("物理")) {
//
//                }else if (attribute.get(i).equals("历史")) {
//
//                }
//            case 2:
//                if (attribute.get(i).equals("生物")) {
//
//                }else if(attribute.get(i).equals("化学")) {
//
//                }else if(attribute.get(i).equals("政治")) {
//
//                }else if(attribute.get(i).equals("地理")) {
//
//                }
//            case 3:
//                characterList.removeIf(character -> !character.getMajorname().equals(attribute.get(i)));
//        }
//        return characterList;
//    }

}
