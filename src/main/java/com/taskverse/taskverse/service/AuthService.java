package com.taskverse.taskverse.service;

import com.taskverse.taskverse.dto.AuthResponse;
import com.taskverse.taskverse.dto.LoginRequest;
import com.taskverse.taskverse.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
