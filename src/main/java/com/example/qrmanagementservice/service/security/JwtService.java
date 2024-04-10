package com.example.qrmanagementservice.service.security;

import org.springframework.security.core.Authentication;

public interface JwtService {
    Authentication parseToken(String jwt);
}
