package com.example.backend.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String username;
  private String password;
  private String email;
  private Boolean enabled;
}
