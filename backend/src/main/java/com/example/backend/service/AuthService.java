package com.example.backend.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.dto.RegisterRequest;
import com.example.backend.entity.NotificationEmail;
import com.example.backend.entity.User;
import com.example.backend.entity.VerificationToken;
import com.example.backend.exception.CustomException;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final VerificationTokenRepository verificationTokenRepository;
  private final MailService mailService;

  @Transactional
  public void signup(RegisterRequest registerRequest) {
    User user = new User(
      registerRequest.getUsername(),
      passwordEncoder.encode(registerRequest.getPassword()),
      registerRequest.getEmail(),
      false);

    userRepository.save(user);

    String token = generateVerificationToken(user);

    mailService.sendMail(new NotificationEmail("Please Activate your Account",
      user.getEmail(),
        "Please click on the below url to activate your account : " +
        "http://localhost:8080/api/auth/verification/" + token));

    log.info("Mail was sent to " + user.getEmail() + " with token: " + token);
  }

  private String generateVerificationToken(User user) {
    String token = UUID.randomUUID().toString();
    VerificationToken verificationToken = new VerificationToken(token, user);

    verificationTokenRepository.save(verificationToken);
    return token;
  }

  @Transactional
  public void verification(String token) throws CustomException {
    VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
      .orElseThrow(() -> new CustomException("Token not found"));

    User user = userRepository.findByUsername(verificationToken.getUser().getUsername())
      .orElseThrow(() -> new CustomException("User not found"));
    
    if(verificationToken.getExpiresAt().isBefore(Instant.now())) {
      throw new CustomException("Verification token is expired");
    }

    user.setEnabled(true);
    userRepository.save(user);
  }
}
