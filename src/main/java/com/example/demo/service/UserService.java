package com.example.demo.service;

import com.example.demo.model.User;
import jakarta.persistence.EntityNotFoundException;

public interface UserService {
    User register(User user);
    String login(String email, String password);
    User getUserByEmail(String email) throws EntityNotFoundException;
}
