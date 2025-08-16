package com.takeshi.library.service;

import com.takeshi.library.entity.UserEntity;
import com.takeshi.library.domain.auth.AuthResult;
import com.takeshi.library.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    @Override
    public AuthResult authenticate(String email, String password) {
        UserEntity userEntity = userMapper.findByEmail(email);
        if (userEntity == null) {
            System.out.println("userEntity == null");
            return AuthResult.EMAIL_NOT_FOUND;
        }
        if(!checkPassword(password, userEntity.getPassword())){
            System.out.println("!checkPassword(password, userEntity.getPassword())");
            return AuthResult.INVALID_PASSWORD;
        }

        return AuthResult.SUCCESS;
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, hashedPassword);
    }

}
