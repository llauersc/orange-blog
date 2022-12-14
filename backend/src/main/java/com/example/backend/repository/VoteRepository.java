package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
  
}
