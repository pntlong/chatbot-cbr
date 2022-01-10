package com.example.chatbotcbr.service;

import com.example.chatbotcbr.entities.Solution;
import com.example.chatbotcbr.entities.UserInfo;
import com.example.chatbotcbr.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> getAll(){
        return userInfoRepository.findAll();
    }

    public List<Solution> getAllSolution() {
        return null;
    }
}
