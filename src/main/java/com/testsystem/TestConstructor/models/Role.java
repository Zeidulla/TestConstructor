package com.testsystem.TestConstructor.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "role")
    private List<User> users;

    public Role(){}
    public  Role(String role) {/* сделаю пометку */
        if(role.equals("user")==true) {
            id = Long.valueOf("1");
            name = "ROLE_USER";
        } else{
            id = Long.valueOf("2");
            name = "ROLE_TESTER";
        }
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
