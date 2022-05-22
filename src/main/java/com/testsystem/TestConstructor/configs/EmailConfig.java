package com.testsystem.TestConstructor.configs;


import java.util.Properties;   //Файловое расширение для файлов, которые используются в основном в технологиях Java для хранения конфигурационных параметров программы. Они также могут использоваться для хранения переменных строчной типа для локализации; они известны как Property Resource Bundles

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;       //Spring-Bean для MailSender
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;   //Интерфейс верхнего уровня, обеспечивающий базовую функциональность для отправки простых электронных писем
import org.springframework.mail.javamail.JavaMailSenderImpl; //JavaMailSenderImpl предоставляет реализацию интерфейса JavaMailSender

    @Configuration
    public class EmailConfig {

        @Value("${mail.host}")
        private String host;

        @Value("${mail.username}")
        private String username;

        @Value("${mail.from}")
        private String from;

        @Value("${mail.password}")
        private String password;

        @Value("${mail.port}")
        private int port;

        @Value("${mail.protocol}")
        private String protocol;

        @Value("${mail.debug}")
        private String debug;

        @Bean
        public JavaMailSender getJavaMailSender() {                  //Это подинтерфейс (subinterface) у MailSender, он поддерживает сообщения вида MIME, он обычно используется с классом MimeMessageHelper чтобы создать MimeMessage
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); //Свойства почты,
            mailSender.setHost(host);            //необходимые для указания SMTP-сервера,
            mailSender.setPort(port);             //могут быть определены с помощью JavaMailSenderImpl
            mailSender.setUsername(username);      //https://javascopes.com/spring-email-c7972a9a/
            mailSender.setPassword(password);

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", protocol);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", debug);
            props.put("mail.from", from);

            return mailSender;
        }
    }
