package com.testsystem.TestConstructor.service;

import com.testsystem.TestConstructor.models.Test;
import com.testsystem.TestConstructor.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;  //Поддерживает форматирование текста
import org.springframework.mail.javamail.JavaMailSender; //Для отправки электронной почты
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;       //базовая функциональность для отправки простых электронных писем

    public void send(String mailTo, String subject, String text) { //https://betacode.net/11145/spring-email
        SimpleMailMessage message = new SimpleMailMessage(); // Создание простого
        message.setTo(mailTo);                               // почтового сообщения
        message.setSubject(subject);                         // включающего поля
        message.setText(text);                               // from, to, cc, subject и text
        this.emailSender.send(message); // Отправка почтового сообщение.
    }

    public void sendActivationUrl(User u) {
        send(u.getEmail(), "Активация аккаунта", "Активация для пользователя " + u.getUsername() + ". Для активации аккаунта перейдите по ссылке http://localhost:8080/confirmRegistration/" + u.getActivationCode());
    }
    public void sendRecoverUrl(User u) {
        send(u.getEmail(), "Восстановление пароля", "Восстановление пароля для " + u.getUsername() + ". Для восстановления аккаунта перейдите по ссылке http://localhost:8080/recoverPassword/" + u.getActivationCode());
    }

    public void sendTestResult(String username, Test test, int result, int count) {
        send(test.getUser().getEmail(), "Кто-то прошёл ваш тест!", "Здравствуйте! Пользователь " + username + " прошел ваш тест! Его результат " + result+"/"+count);
   }

}
