package com.example.chatbotcbr.controller;

import com.example.chatbotcbr.entities.Solution;
import com.example.chatbotcbr.entities.UserInfo;
import com.example.chatbotcbr.service.ChatbotService;
import com.example.chatbotcbr.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatbotController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ChatbotService chatbotService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayChat() {
        return "index.html";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    List<UserInfo> getAll() {
        List<UserInfo> list = userInfoService.getAll();
        return list;
    }

    @RequestMapping(value = "/get-solution", method = RequestMethod.GET)
    public @ResponseBody
    List<Solution> getAllSolution() {
        List<Solution> list = userInfoService.getAllSolution();
        return list;
    }
    @CrossOrigin(origins = "http://localhost:8080/find-solution")
    @RequestMapping(value = "/find-solution", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Solution> processSolution(@RequestBody UserInfo userInfo) {
        Solution solution = chatbotService.processSolution(userInfo);
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }
}
