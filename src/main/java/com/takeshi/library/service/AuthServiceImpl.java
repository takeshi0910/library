package com.takeshi.library.service;

import com.takeshi.library.mapper.UserMapper;
import com.takeshi.library.model.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    public AuthServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean authenticate(String email, String password) {
        User user = userMapper.findByEmail(email);
        if (user == null) {
            return false;
        }
        return checkPassword(password, user.getPassword());
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        // BCryptで照合
        return new BCryptPasswordEncoder().matches(rawPassword, hashedPassword);
    }

}
