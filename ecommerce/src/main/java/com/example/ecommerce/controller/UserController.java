package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.UserResponse;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.security.JwtUtil;
import com.example.ecommerce.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;  

    // REGISTER
    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {
         return service.register(user);
    }
    
    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User user = service.login(request);
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

    // GET ALL
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return service.getAllUsers();
    }
    
    // CURRENT USER
    @GetMapping("/me")
    public User getCurrentUser(HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        String token = header.substring(7);

        String email = jwtUtil.extractEmail(token);

        return service.getUserByEmail(email);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "Deleted";
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, 
    		@Valid @RequestBody User user) {
        return service.updateUser(id, user);
    }
}