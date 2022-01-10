package com.example.chatbotcbr.service;

import com.example.chatbotcbr.entities.Solution;
import com.example.chatbotcbr.entities.UserInfo;
import com.example.chatbotcbr.repository.SolutionRepository;
import com.example.chatbotcbr.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatbotService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    public ChatbotService() {
    }

    public Solution processSolution(UserInfo userInfo) {
        int age = userInfo.getAge();
        float bmi = userInfo.getBmi();
        int activity = userInfo.getActivity_level();
        if (userInfoRepository.findUserInfoByAgeAndBmiAndActivity_level(age, bmi, activity) != null) {
            UserInfo info = userInfoRepository.findUserInfoByAgeAndBmiAndActivity_level(age, bmi, activity);
            return solutionRepository.findSolutionById(info.getId());
        } else {
            List<UserInfo> list = userInfoRepository.findAll();
            HashMap<Integer, Float> hashMap = new HashMap<>();
            for (UserInfo i : list) {
                float ageSimilarity = age <= i.getAge() ? age / i.getAge() : i.getAge() / age;
                float bmiSimilarity = bmi <= i.getBmi() ? bmi / i.getBmi() : i.getBmi() / bmi;
                float activitySimilarity = activity <= i.getActivity_level() ? activity / i.getActivity_level() : i.getActivity_level() / activity;
                float similarity = 3 * ageSimilarity + 2 * bmiSimilarity + activitySimilarity;
                hashMap.put(i.getId(), similarity);
            }
            hashMap = sortHashMapByValues(hashMap);
            System.out.println(hashMap);

            System.out.println(hashMap.keySet().toArray()[hashMap.size()-1]);
            return solutionRepository.findSolutionById((Integer) hashMap.keySet().toArray()[hashMap.size()-1]);
        }
    }

    public LinkedHashMap<Integer, Float> sortHashMapByValues(
            HashMap<Integer, Float> passedMap) {
        List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Float> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Integer, Float> sortedMap =
                new LinkedHashMap<>();

        Iterator<Float> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Float val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                Float comp1 = passedMap.get(key);
                Float comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
}
