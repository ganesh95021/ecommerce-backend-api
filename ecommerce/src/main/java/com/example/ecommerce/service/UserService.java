package com.example.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.UserResponse;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    login Method
    public User login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
     // PASSWORD MATCH
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    // REGISTER METHOD 
    public User register(User user) {

//        System.out.println("SERVICE PASSWORD = " + user.getPassword()); // debug

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        // 🔥 PASSWORD ENCODE
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return repository.save(user);
    }
  
//     updateUser Method
    public User updateUser(Long id, User user) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
     // 🔥 PASSWORD ENCODE
        existingUser.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        existingUser.setRole(user.getRole());

        
        return repository.save(existingUser);
    }
  
//     UserResponse method 
    public List<UserResponse> getAllUsers() {

        List<User> users = repository.findAll();
        List<UserResponse> list = new ArrayList<>();

        for (User user : users) {
            UserResponse res = new UserResponse();
            res.setId(user.getId());
            res.setName(user.getName());
            res.setEmail(user.getEmail());
            res.setRole(user.getRole());

            list.add(res);
        }

        return list;
    }
    
    public User getUserByEmail(String email) {

        User user = repository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }
}