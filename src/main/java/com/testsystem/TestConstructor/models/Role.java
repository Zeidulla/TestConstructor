package com.testsystem.TestConstructor.models;

//https://habr.com/ru/post/482552/

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Role implements GrantedAuthority { //класс Role реализовывает интерфейс GrantedAuthority,
    @Id                                        //в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "role")
    private List<User> users;

    public Role(){}
    public  Role(String role){
        if(role.equals("user")==true) {
            id = Long.valueOf("1");
            name = "ROLE_USER";
        } else if(role.equals("tester")==true){
            id = Long.valueOf("2");
            name = "ROLE_TESTER";
        } else {
            id = Long.valueOf("3");
            name = "ROLE_ADMIN";
        }
    }
    public Long getId() {  //публичный конструктор: только id
        return id;
    }

    public void setId(Long id) {  //публичный конструктор: только id
        this.id = id;
    }

    public String getName() { //публичный конструктор: только id и name
        return name;
    }

    public void setName(String name) { //публичный конструктор: только id и name
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
