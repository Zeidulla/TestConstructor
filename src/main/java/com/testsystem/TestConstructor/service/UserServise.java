package com.testsystem.TestConstructor.service;
import com.testsystem.TestConstructor.models.Role;
import com.testsystem.TestConstructor.models.User;
import com.testsystem.TestConstructor.repository.ResultRepository;
import com.testsystem.TestConstructor.repository.TestRepository;
import com.testsystem.TestConstructor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    EmailService emailService;

    @Autowired
    TestService testService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    TestRepository testRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails u=(UserDetails) userRepository.findByUsername(username);
        if (u==null){
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            return u;
        }
    }
    public int registerUser(String username,String password,String emaill,String role){
        if(userRepository.findByEmail(email)!=null)return -1;
        if(userRepository.findByEmail(email)!=null)return -2;
        User u = new User(username,bCryptPasswordEncoder.encode(password),email);
        u.setActived(false);
        u.setActivationCode(UUID.randomUUID().toString());
        emailService.sendActivationUrl(u);
        u.setRole(Collections.singletonList(new Role(role)));
        userRepository.save(u);
        return 0;
    }
    public boolean recoverPassword(String email){
        User u = userRepository.findByEmail(email);
        if(u==null) return false;
        u.setActivationCode(UUID.randomUUID().toString());
        emailService.sendRecoverUrl(u);
        userRepository.save(u);
        return true;
    }
    public boolean recPassword(String activationCode, String password){
        User u = userRepository.findByActivationCode(activationCode);
        if(u!=null) {
            u.setPassword(bCryptPasswordEncoder.encode(password));
            u.setActivationCode(null);
            userRepository.save(u);
            return true;
        }
        else{
            return false;
        }

    }
}
