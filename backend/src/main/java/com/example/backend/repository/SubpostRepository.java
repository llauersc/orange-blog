package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Subpost;

public interface SubpostRepository extends JpaRepository<Subpost, Long> {
  
}
