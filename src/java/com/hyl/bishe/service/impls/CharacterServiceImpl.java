package com.hyl.bishe.service.impls;

import com.hyl.bishe.dao.CharacterDao;
import com.hyl.bishe.entity.Character;
import com.hyl.bishe.entity.CharacterAndSchoolInfo;
import com.hyl.bishe.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterDao characterDao;
    @Override
    public List<Character> findByGradeBetween(int start, int end, String location, String leibie) {
        return characterDao.findCharactersByGradeBetweenAndLocationAndLeibie(start, end, location, leibie);
    }
    public List<Character> findByCharacter(int start, int end, String location, String leibie,String province) {
        return characterDao.findCharacterByGradeBetweenAndLocationAndLeibie(start,end,location,leibie,province);
    }
    @Override
    public List<String> findLocation() {
        return characterDao.findDistinctLocation();
    }
    public void findAll(){
    }
    public List<Character> findByGradeBetweenAndLocationAndMajorname(int start, int end, String location, String major){
        return characterDao.findCharactersByGradeBetweenAndLocationAndMajorname(start, end, location, major);
    }
}
