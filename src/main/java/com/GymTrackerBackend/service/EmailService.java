package com.GymTrackerBackend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(String to, String link) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to); // DESTINATARIO = usuario registrado
        message.setFrom("no-reply@gymtracker.com"); // REMITENTE (puede ser cualquiera)
        message.setSubject("Verifica tu cuenta - GymTracker");
        message.setText(
                "Hola!\n\n" +
                "Gracias por registrarte en GymTracker.\n" +
                "Para activar tu cuenta, haz clic en el siguiente enlace:\n\n" +
                link + "\n\n" +
                "Si no solicitaste esta cuenta, ignora este mensaje."
        );

        mailSender.send(message);
    }
}