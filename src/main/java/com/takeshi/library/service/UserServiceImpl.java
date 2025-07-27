package com.takeshi.library.service;

import com.takeshi.library.mapper.UserMapper;
import com.takeshi.library.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public UserServiceImpl(PasswordEncoder encoder, UserMapper userMapper) {
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> searchUsers(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return userMapper.findAll(); // 削除されてない全件
        }
        return userMapper.findByKeyword(keyword);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void insert(User user) {
        String hashed = encoder.encode(user.getPassword()); // ← ここでハッシュ化
        user.setPassword(hashed);
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        String hashed = encoder.encode(user.getPassword()); // ← ここでハッシュ化！
        user.setPassword(hashed);
        userMapper.update(user);
    }

    @Override
    public void softDelete(Long id) {
        userMapper.softDelete(id); // 論理削除（deleted = true）
    }

}
