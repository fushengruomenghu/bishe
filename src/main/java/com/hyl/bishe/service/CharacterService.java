package com.hyl.bishe.service;

import com.hyl.bishe.entity.Character;

import java.util.List;

public interface CharacterService {
    List<Character> findByGradeBetween(int start,int end,String location,String leibie);
    List<String> findLocation();
}
