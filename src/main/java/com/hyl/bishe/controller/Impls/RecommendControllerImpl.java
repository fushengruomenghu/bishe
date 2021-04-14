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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        List<String> leibie= scorelineService.findleibie();
        model.addAttribute("province",province);
        model.addAttribute("leibie",leibie);
        model.addAttribute("location",location);
        model.addAttribute("active3","active");
        model.addAttribute("color3","background-color: #ff8f8f");
        return "recommend";
    }


    @ResponseBody
    @RequestMapping(value = "/doRecommend")
    public List<Character> doRecommend(@RequestParam(value = "location") String location, @RequestParam(value = "province") String province,@RequestParam(value = "graderange",defaultValue = "0") String graderange,
                                       @RequestParam(value = "leibie") String leibie, @RequestParam(value = "grade", defaultValue = "600") String grade, @RequestParam String preference,
                                       @RequestParam(value = "course1") String course1, @RequestParam(value = "course2") String course2, @RequestParam(value = "course3") String course3) {

        int changji = Integer.parseInt(grade);
        int range = Integer.parseInt(graderange);
        List<Character> characterList;
        List<Profession> professionList=new ArrayList<>();
        List<SchoolInfo> schoolInfoList=new ArrayList<>();
        List<Character> characters=new ArrayList<>();
        List<Character> characters1=new ArrayList<>();


        
        System.out.println(leibie+","+location);
        if (province.equals("province")) {
             characterList= characterService.findByGradeBetween(changji - range, changji + range, location, leibie);
        }else {
            characterList=characterService.findByCharacter(changji - range, changji + range, location, leibie,province);
        }

        List<String> list = new ArrayList<>();

        list.add(location);
        list.add(province);
        list.add(leibie);
        list.add(grade);
        list.add(course1);
        list.add(course2);
        list.add(course3);
        System.out.println(list);
        characterList = reSimilarity(GetByAttribute(characterList, list));
        for (Character character:characterList){
            professionList.add(professionService.findbyName(character.getMajorname()));
        }
        professionList.sort(Comparator.comparing(Profession::getRanks).reversed());
        int i=0;
        /**
         * 根据专业排名过滤
         */
        while (i<characterList.size()){
            for (Character character:characterList){
                if (character.getMajorname().equals(professionList.get(i).getProname())) {
                    characters.add(character);
                }
            }
            i++;
        }
//        for (Character character:characters){
//            schoolInfoList.add(schoolInfoService.findByName(character.getCollege()));
//        }
//        if (preference != null) {
//            if (preference.equals("environment")) {
//                schoolInfoList.sort(Comparator.comparing(SchoolInfo::getEnvironment).reversed());
//            }else if(preference.equals("comprehensive")){
//                schoolInfoList.sort(Comparator.comparing(SchoolInfo::getComprehensive).reversed());
//            }else {
//                schoolInfoList.sort(Comparator.comparing(SchoolInfo::getLife).reversed());
//            }
//            for (Character character:characters){
//                for (SchoolInfo schoolInfo:schoolInfoList){
//                    if (character.getCollege().equals(schoolInfo.getSchname())) {
//                        characters1.add(character);
//                    }
//                }
//            }
//        }
//        for (int j = 0; j < characters1.size(); j++) {
//            for (int k = j+1; k < characters1.size(); k++) {
//                if (characters1.get(j).getId().equals(characters1.get(k).getId())) {
//                    characterList.remove(characterList.get(k));
//                }
//            }
//        }
//        if (characters1.size()>5) {
//            for (int j=0;j<characters1.size();j++){
//                if (j >5) {
//                    characters1.remove(characters1.get(j));
//                }
//
//            }
//        }
        return characters;
    }
    public List<Character> GetByAttribute(List<Character> characterList,List<String> attribute){
        List<Character> characters=new ArrayList<>();
        List<String> list1=new ArrayList<>();
        for (int i = 4; i < attribute.size(); i++) {
            String str=attribute.get(i);
            switch (i){
                case 4:
                    if(str.equals("语文")||str.equals("英语")) {
                        list1=addList(list1,"%文学%","%语言%","%新闻%");
                    } else {
                        list1=addList(list1,"%数学%","%经济%","%金融%");
                    }
                    break;
                case 5:
                    if (str.equals("物理")) {
                        list1=addList(list1,"%机械%","%电气%","%工程%");

                    }else if(str.equals("历史")) {
                        list1=addList(list1,"%文学%","%历史%","%政治%");
                    }
                    break;
                case 6:
                    if (str.equals("生物")) {
                        list1=addList(list1,"%生物%","%农%","%医%");

                    }else if(str.equals("化学")) {
                        list1=addList(list1,"%材料%","%化学%","%食品%");

                    }else if(str.equals("政治")) {
                        list1=addList(list1,"%政治%","%法%","%理论%");

                    }else if(str.equals("地理")) {
                        list1=addList(list1,"%地质%","%测绘%","%测绘%");
                    }
                    break;
            }
        }
        for (Character character:characterList){
            for (String s:list1){
                if (s.equals(character.getMajorname())) {
                    characters.add(character);
                }
            }
        }
        return characters;
    }
    public List<String> addList(List<String> list1,String name1,String name2,String name3){
        List<String> lists=professionService.findProNameByDegreeCategory(name1,name2,name3);
        for (String list:lists){
            list1.add(list);
        }
        return list1;
    }
    public List<Character> reSimilarity(List<Character> characterList){
        for (int i = 0; i < characterList.size(); i++) {
            for (int j = i+1; j < characterList.size(); j++) {
                if (characterList.get(i).getCollege().equals(characterList.get(j).getCollege())
                        &&characterList.get(i).getMajorname().equals(characterList.get(j).getMajorname())
                        &&characterList.get(i).getGrade().equals(characterList.get(j).getGrade())) {
                    characterList.remove(characterList.get(j));
                }
            }
        }
        return characterList;
    }
}

