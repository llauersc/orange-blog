package com.example.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.RegisterRequest;
import com.example.backend.exception.CustomException;
import com.example.backend.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;
  
  @PostMapping("/signup")
  public void signup(@RequestBody RegisterRequest registerRequest) {
    authService.signup(registerRequest);
  }

  @GetMapping("/verification/{token}")
  public ResponseEntity<String> verification(@PathVariable String token) {

    try {
      authService.verification(token);
    } catch(CustomException e) {
      return new ResponseEntity<>("Link is expired", HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>("Account verified", HttpStatus.OK);
  }
}