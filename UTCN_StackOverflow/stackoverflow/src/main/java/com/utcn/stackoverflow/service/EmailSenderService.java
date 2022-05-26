package com.utcn.stackoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String username) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("bogdantudoralex@gmail.com");
        message.setSubject("Banned User");
        message.setText(username + " has been banned!");
        message.setTo(username + "@gmail.com");

        mailSender.send(message);
        System.out.println("Mail send");
    }

}
