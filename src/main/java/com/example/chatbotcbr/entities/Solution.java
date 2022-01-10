package com.example.chatbotcbr.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "solution")
public class Solution {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Column(name = "status")
    @JsonProperty
    private String status;
    @Column(name = "energy_requirement")
    @JsonProperty
    private String energy_requirement;
    @Column(name = "menu_suggestion")
    @JsonProperty
    private String menu_suggestion;

    public Solution() {
    }

    public Solution(String status, String energy_requirement, String menu_suggestion) {
        this.status = status;
        this.energy_requirement = energy_requirement;
        this.menu_suggestion = menu_suggestion;
    }

    public Solution(Integer id, String status, String energy_requirement, String menu_suggestion) {
        this.id = id;
        this.status = status;
        this.energy_requirement = energy_requirement;
        this.menu_suggestion = menu_suggestion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnergy_requirement() {
        return energy_requirement;
    }

    public void setEnergy_requirement(String energy_requirement) {
        this.energy_requirement = energy_requirement;
    }

    public String getMenu_suggestion() {
        return menu_suggestion;
    }

    public void setMenu_suggestion(String menu_suggestion) {
        this.menu_suggestion = menu_suggestion;
    }

    @Override
    public String toString() {
        return status + " " + energy_requirement + " " + menu_suggestion;
    }
}
