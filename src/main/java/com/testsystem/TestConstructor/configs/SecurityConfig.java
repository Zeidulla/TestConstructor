package com.testsystem.TestConstructor.configs;

//https://sysout.ru/nastrojka-jdbc-autentifikatsii-v-spring-security/

import com.testsystem.TestConstructor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  ////эта аннотация используется для классов, которые определяют bean-компоненты
@EnableWebSecurity //@EnableWebSecurity включает поддержку web security и обеспечивает интеграцию со Spring MVC. сonfigure(HttpSecurity) определяет какой URL нужно защищать, а какой не надо(по умолчанию все защищено)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override //За авторизацию отвечает метод configure(HttpSecurity http):
    protected void configure(HttpSecurity http) throws Exception { //для авторизации
        http.authorizeRequests()
                .antMatchers("/register","/confirmRegistration/**","/recoverPass","/recoverPassword/**").anonymous()
                .antMatchers("/*.css","/*.js").permitAll()  //    / для всех пользователей (в том числе не аутентифицированных)
                .antMatchers("/test/**").hasRole("TESTER")  //  /test для пользователей с ролью TESTER
                .antMatchers("/admin/**", "/delete-user/**").hasRole("ADMIN") //  /admin  для пользователей с ролью ADMIN
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired //За настройку аутентификации отвечает метод configure(AuthenticationManagerBuilder auth)
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { //для аутентификации
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

}