package com.example.backend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.entity.RefreshToken;
import com.example.backend.repository.RefreshTokenRepository;
import com.example.backend.exception.CustomException;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken generateRefreshToken() {
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setToken(UUID.randomUUID().toString());

    return refreshTokenRepository.save(refreshToken);
  }

  void validateRefreshToken(String token) {
    refreshTokenRepository.findByToken(token)
        .orElseThrow(() -> new CustomException("Invalid refresh Token"));
  }

  public void deleteRefreshToken(String token) {
    refreshTokenRepository.deleteByToken(token);
  }
}