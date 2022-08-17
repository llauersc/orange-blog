package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Subpost;

@Repository
public interface SubpostRepository extends JpaRepository<Subpost, Long> {
  
}
