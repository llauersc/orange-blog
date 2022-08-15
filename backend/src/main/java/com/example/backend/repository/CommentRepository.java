package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  
}
