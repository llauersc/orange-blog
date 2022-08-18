package com.example.backend.controller;

import java.time.Instant;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.AuthenticationResponse;
import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.exception.CustomException;
import com.example.backend.security.JwtProvider;
import com.example.backend.service.AuthService;
import com.example.backend.service.RefreshTokenService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;
  private final RefreshTokenService refreshTokenService;

  @PostMapping("/signup")
  public void signup(@RequestBody RegisterRequest registerRequest) {
    authService.signup(registerRequest);
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
    Authentication authenticate = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authenticate);
    String token = jwtProvider.generateToken(authenticate);
    return AuthenticationResponse.builder()
        .authenticationToken(token)
        .refreshToken(refreshTokenService.generateRefreshToken().getToken())
        .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMs()))
        .username(loginRequest.getUsername())
        .build();
  }

  @GetMapping("/verification/{token}")
  public ResponseEntity<String> verification(@PathVariable String token) {

    try {
      authService.verification(token);
    } catch (CustomException e) {
      return new ResponseEntity<>("Link is expired", HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>("Account verified", HttpStatus.OK);
  }
}