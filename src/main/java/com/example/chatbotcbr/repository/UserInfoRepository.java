package com.example.chatbotcbr.repository;

import com.example.chatbotcbr.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    public UserInfo findAllById(Integer id);

    public List<UserInfo> findAll();

    @Query(value = "SELECT * FROM user_info ui " +
            "WHERE :age = ui.age " +
            "AND :bmi = ui.bmi " +
            "AND :activity = ui.activity_level",nativeQuery = true)
    public UserInfo findUserInfoByAgeAndBmiAndActivity_level(@Param("age") int age,
                                                             @Param("bmi") float bmi,
                                                             @Param("activity") int activity);
    @Query(value = "select * from user_info where exists " +
            "(select ui.* from user_info ui " +
            "where :age = ui.age " +
            "and :bmi = ui.bmi " +
            "and :activity = ui.activity_level)",nativeQuery = true)
    public boolean existsUserInfoByAgeAndBmiAndActivity_level(@Param("age") int age,
                                                              @Param("bmi") float bmi,
                                                              @Param("activity") int activity);
}
