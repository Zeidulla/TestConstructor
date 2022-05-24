package com.testsystem.TestConstructor.models;

//https://habr.com/ru/post/482552/

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity     //поля класса имеют отображение в БД, Table(name = «t_user») указывает с какой именно таблицей
public class User implements UserDetails {        //Alt+Insert автоматическое заполнение геттеров, сеттеров и тд
    @Id      //GenerationType.IDENTITY параметр IDENTITY значит, что генерацией id будет заниматься БД.
    @GeneratedValue(strategy = GenerationType.AUTO) //вариант – AUTO, hibernate сам выберет из одну стратегий
    private Long id;         //SEQUENCE – использует встроенный в базы данных, такие как PostgreSQL или Oracle, механизм генерации последовательных значений (sequence)
    //TABLE – используется отдельная таблица с проинициализированным значениями ключей
    private String username;

    private String password;

    private String email;

    private String activationCode;

    private boolean isActivated;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Test> test;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Result> result;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public User(){}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActivated;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //Для того, чтобы в дальнейшим использовать класс User в Spring Security, он должен реализовывать интерфейс UserDetails
        return role;    //getAuthorities() он возвращает список ролей пользователя.
    }                     //Поэтому для остальных методов изменяем возвращаемое значение на true

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public List<Test> getTest() {
        return test;
    }

    public void setTest(List<Test> test) {
        this.test = test;
    }

    public void addTest(Test test){
        this.test.add(test);
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
    public void addResult(Result result){
        this.result.add(result);
    }
}