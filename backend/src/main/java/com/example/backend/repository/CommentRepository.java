package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  
}
