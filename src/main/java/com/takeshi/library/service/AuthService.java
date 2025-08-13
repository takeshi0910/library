package com.takeshi.library.service;

import com.takeshi.library.domain.auth.AuthResult;

public interface AuthService {
    AuthResult authenticate(String email, String password);
}

