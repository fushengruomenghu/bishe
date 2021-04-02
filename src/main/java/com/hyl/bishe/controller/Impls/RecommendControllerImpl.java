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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
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
        List<String> pici= scorelineService.findpici();
        model.addAttribute("province",province);
        model.addAttribute("pici",pici);
        model.addAttribute("location",location);
        model.addAttribute("active3","active");
        model.addAttribute("color3","background-color: #ff8f8f");
        return "recommend";
    }


    @ResponseBody
    @RequestMapping("/doRecommend")
    public List<Character> doRecommend(@RequestParam(value = "location") String location, @RequestParam(value = "province") String province,
                                       @RequestParam(value = "leibie", defaultValue = "理科") String leibie, @RequestParam(value = "grade", defaultValue = "600") String grade,
                                       @RequestParam(value = "course1") String course1, @RequestParam(value = "course2") String course2, @RequestParam(value = "course3") String course3){
        int changji=Integer.parseInt(grade);

//        Profession profession=professionService.findbyName("");
//        SchoolInfo schoolInfo=schoolInfoService.findByName("");
        List<Profession> professionList= new ArrayList<>();
        List<SchoolInfo> schoolInfoList= new ArrayList<>();
        List<Character> characterList=characterService.findByGradeBetween(changji-5,changji+5,location,leibie);
        characterList.removeIf(character -> character.getProvince().equals(province));

        List<String> list=new ArrayList<>();
        list.add(location);
        list.add(province);
        list.add(leibie);
        list.add(grade);
        list.add(course1);
        list.add(course2);
        list.add(course3);
        System.out.println(list);
        for (int i = 0; i <list.size() ; i++) {
            try {
                write("test",list.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        characterList=GetByAttribute(characterList,list);
        //List<Character> characters=characterList.subList(0,10);
//        for (Character character:characterList){
//           professionList.add(professionService.findbyName(character.getMajorname()));
//           schoolInfoList.add(schoolInfoService.findByName(character.getCollege()));
//        }
//        professionList.sort(Comparator.comparing(Profession::getRanks));
//        schoolInfoList.sort(Comparator.comparing(SchoolInfo::getComprehensive).reversed());
//        List<Profession> professions=professionList.subList(0,20);
//        List<SchoolInfo> schoolInfos=schoolInfoList.subList(0,20);
//        for (Character character:characterList){
//            for (int i = 0; i < professions.size(); i++) {
//                if (!character.getMajorname().equals(professions.get(i).getProname())) {
//                    characterList.remove(character);
//                }
//            }
//            for (int i = 0; i < schoolInfos.size(); i++) {
//                if(!character.getCollege().equals(schoolInfos.get(i).getSchname())){
//                    characterList.remove(character);
//                }
//            }
//        }
        characterList.sort(Comparator.comparing(Character::getGrade));
        return characterList;
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
    public static void write(String file, String conent) throws IOException {
        File file2 = new File(file);
        if (!file2.exists()) {
            file2.createNewFile();
        }
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(conent + "\r\r");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

