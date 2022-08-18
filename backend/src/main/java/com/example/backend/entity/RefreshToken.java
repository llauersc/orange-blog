package com.example.backend.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String token;

  @CreatedDate
  private Instant createdAt;
}
