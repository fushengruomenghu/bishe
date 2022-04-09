package com.hyl.bishe.controller.Impls;

import com.hyl.bishe.entity.Character;
import com.hyl.bishe.entity.SchoolInfo;
import com.hyl.bishe.entity.Users;
import com.hyl.bishe.service.impls.*;
import com.hyl.bishe.utils.FcmRealize;
import com.sun.javafx.util.Utils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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
    @Autowired
    private UsersServiceImpl usersService;

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
    public List<List<String>> doRecommend(@RequestParam(value = "location") String location, @RequestParam(value = "province") String province, @RequestParam(value = "graderange",defaultValue = "0") String graderange,
                                          @RequestParam(value = "leibie") String leibie, @RequestParam(value = "grade", defaultValue = "600") String grade, @RequestParam String preference,
                                          @RequestParam(value = "Mbti") String mbti, @RequestParam(value = "phone") String phone, @RequestParam(value = "major") String major,HttpServletRequest request) {

        int changji = Integer.parseInt(grade);
        int range = Integer.parseInt(graderange);
        List<Character> characterList = new ArrayList<>();
        List<Character> characters;
        List<Character> characters1 = new ArrayList<>();

        usersService.updateUserGradeByPhone(grade,phone);
        Users users=usersService.findUserByPhone(phone);
        HttpSession session = request.getSession();
        session.setAttribute("Users",users);
        if (major.equals("")) {
            if (province.equals("province")) {
                characters= characterService.findByGradeBetween(changji - range, changji + range, location, leibie);
            }else {
                characters=characterService.findByCharacter(changji - range, changji + range, location, leibie,province);
            }
            double[][] fcmdata=new double[characters.size()][4];

            for (int i=0;i<characters.size();i++){
                fcmdata[i][0]=Double.valueOf(characters.get(i).getId());
                fcmdata[i][1]=Double.parseDouble(characters.get(i).getCpoint());
                fcmdata[i][2]=Double.parseDouble(characters.get(i).getEpoint());
                fcmdata[i][3]=Double.parseDouble(characters.get(i).getLpoint());
            }
            FcmRealize fcmRealize=new FcmRealize(fcmdata,fcmdata.length, 3, 3, 100, 2, 0.0001);
            double[][] recenter= fcmRealize.runFcm_myself();
            List<Double> list=GetFcm(fcmdata,recenter);

            for (int j = 0; j < list.size(); j++) {
                for (int i = 0; i < characters.size(); i++){
                    if (list.get(j)-Double.valueOf(characters.get(i).getId())==0.0) {
                        characters1.add(characters.get(i));
                    }
                }
            }
            for (int i = 0; i < characters1.size(); i++) {
                if (professionService.findMbtiById(characters1.get(i).getPid()).matches(".*"+mbti+".*")) {
                    characterList.add(characters1.get(i));
                }
            }
        }else {
            characterList=characterService.findByGradeBetweenAndLocationAndMajorname(changji - range, changji + range, location, major);
            if (!province.equals("province")){
                characterList.removeIf(character -> !character.getProvince().equals(province));
            }
        }

        characterList=reSimilarity(characterList);
        List<List<String>> listList=SortList(characterList);
        if(listList.size()>10){
            listList=listList.subList(0,10);
        }
        session.setAttribute("Remsg","推荐成功");
        return listList;
    }


    public List<Character> reSimilarity(List<Character> characterList){
        for (int i = 0; i <characterList.size() ; i++) {
            for (int j = characterList.size()-1; j >i; j--) {
                if (characterList.get(i).getPid().equals(characterList.get(j).getPid())
                        &&characterList.get(i).getSid().equals(characterList.get(j).getSid())) {
                    characterList.remove(characterList.get(j));
                }
            }
        }

        return characterList;
    }
    public List<List<String>> SortList(List<Character> characterList){
        SchoolInfo schoolInfo=new SchoolInfo();
        Map<String,List<String>> lists=new HashMap<>();
        List<List<String>> list2=new ArrayList<>();
        double a =0.315,b = 0.465,c = 0.22;

        for (Character character : characterList) {
            List<String> list = new ArrayList<>();
            list.clear();
            list.add(character.getCollege());
            list.add(character.getProvince());
            list.add(character.getMajorname());
            list.add(character.getGrade());
            schoolInfo = schoolInfoService.findByName(character.getCollege());
            Double sim = a * Double.parseDouble(schoolInfo.getEnvironment()) + b * Double.parseDouble(schoolInfo.getComprehensive()) + c * Double.parseDouble(schoolInfo.getLife());

            list.add(String.valueOf(sim));
            lists.put(character.getCollege(), list);

        }

        for(String key : lists.keySet()){
            list2.add(lists.get(key));
        }
        Collections.sort(list2, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o2.get(4).compareTo(o1.get(4));
            }
        });
        return list2;
    }
    public List<Double> GetFcm(double[][] Fcmlist, double[][] recenter){
        double[] arr=new double[3];
        List<Double> lists=new ArrayList<>();
        double max=recenter[0][0]+recenter[0][1]+recenter[0][2];

        for (double[] doubles : recenter) {
            if (max < doubles[0] + doubles[1] + doubles[2]) {
                max = doubles[0] + doubles[1] + doubles[2];
            }
        }
        for (double[] doubles : recenter) {
            if (max == doubles[0] + doubles[1] + doubles[2]) {
                arr[0]=doubles[0];
                arr[1]=doubles[1];
                arr[2]=doubles[2];
            }
        }
        for (int i=0;i<Fcmlist.length;i++){
            double num=Math.sqrt(Math.pow(Fcmlist[i][1]-arr[0],2)+Math.pow(Fcmlist[i][1]-arr[1],2)+Math.pow(Fcmlist[i][1]-arr[2],2));
            if (num<0.3) {
                lists.add(Fcmlist[i][0]);
            }
        }
        return lists;
    }

}


