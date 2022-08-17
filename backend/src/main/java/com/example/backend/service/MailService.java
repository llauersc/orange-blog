package com.example.backend.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.backend.entity.NotificationEmail;
import com.example.backend.exception.CustomException;

@Service
@AllArgsConstructor
public class MailService {

  private final JavaMailSender mailSender;

  @Async
  void sendMail(NotificationEmail notificationEmail) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("blog@email.com");
      messageHelper.setTo(notificationEmail.getRecipient());
      messageHelper.setSubject(notificationEmail.getSubject());
      messageHelper.setText(notificationEmail.getBody());
    };
    try {
      mailSender.send(messagePreparator);
    } catch (MailException e) {
      throw new CustomException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
    }
  }
}