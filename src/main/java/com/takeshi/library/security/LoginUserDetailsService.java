package com.takeshi.library.security;

import com.takeshi.library.mapper.UserMapper;
import com.takeshi.library.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userMapper.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + email);
        }
        return new LoginUser(userEntity);
    }
}