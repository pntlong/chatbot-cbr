package com.example.chatbotcbr.repository;

import com.example.chatbotcbr.entities.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Integer> {
    public Solution findSolutionById(int id);
}
