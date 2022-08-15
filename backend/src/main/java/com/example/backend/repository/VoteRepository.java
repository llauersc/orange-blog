package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
  
}
