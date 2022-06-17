package com.fedag.recruitmentSystem.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailSendlerService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void send(String email, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    public void sendHtmlEmail(String email, String subject, String htmlMessage) throws MessagingException {
        MimeMessage mimeMsg = mailSender.createMimeMessage();
        MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMsg, false, "utf-8");
        boolean isHTML = true;
        msgHelper.setFrom(username);
        msgHelper.setTo(email);
        msgHelper.setSubject(subject);
        msgHelper.setText(htmlMessage, isHTML);
        mailSender.send(mimeMsg);
    }
}
