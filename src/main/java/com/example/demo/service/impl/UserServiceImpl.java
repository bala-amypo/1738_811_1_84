
package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User register(User user) {
        return repo.save(user);
    }

    @Override
    public User login(String email, String password) {
        return repo.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }
}
