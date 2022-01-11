package com.example.chatbotcbr.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "age")
    @JsonProperty
    private int age;
    @JsonProperty
    @Column(name = "bmi")
    private float bmi;
    @Column(name = "activity_level")
    @JsonProperty
    private int activity_level;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "solution_id")
    private Solution solution;

    public UserInfo(Integer id, int age, float bmi, int activity_level, Solution solution) {
        this.id = id;
        this.age = age;
        this.bmi = bmi;
        this.activity_level = activity_level;
        this.solution = solution;
    }

    public UserInfo(int age, float bmi, int activity_level, Solution solution) {
        this.age = age;
        this.bmi = bmi;
        this.activity_level = activity_level;
        this.solution = solution;
    }

    public UserInfo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public int getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(int activity_level) {
        this.activity_level = activity_level;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return solution.toString();
    }
}
