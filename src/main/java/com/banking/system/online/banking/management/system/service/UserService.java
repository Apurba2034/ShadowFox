package com.banking.system.online.banking.management.system.service;

import com.banking.system.online.banking.management.system.dto.LoginRequest;
import com.banking.system.online.banking.management.system.dto.RegisterRequest;
import com.banking.system.online.banking.management.system.entity.User;
import com.banking.system.online.banking.management.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User registerUser(RegisterRequest dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return userRepository.save(user);
    }

    public User loginUsers(LoginRequest dto){
        User user=userRepository.findByEmail(dto.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid Email"));
        if(!user.getPassword().equals(dto.getPassword())){
            throw new RuntimeException("Invalid User");
        }
        return user;
    }
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
