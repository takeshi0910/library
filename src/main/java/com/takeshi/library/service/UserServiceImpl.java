package com.takeshi.library.service;

import com.takeshi.library.entity.UserEntity;
import com.takeshi.library.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Override
    public List<UserEntity> searchUsers(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return userMapper.findAll(); // 削除されてない全件
        }
        return userMapper.findByKeyword(keyword);
    }

    @Override
    public UserEntity findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void insert(UserEntity userEntity) {
        String hashed = encoder.encode(userEntity.getPassword()); // ハッシュ化
        userEntity.setPassword(hashed);
        userMapper.insert(userEntity);
    }

    @Override
    public void update(UserEntity userEntity) {
        String rawPassword = userEntity.getPassword();

        if (rawPassword != null && !rawPassword.isBlank()) {
            String hashed = encoder.encode(rawPassword);
            userEntity.setPassword(hashed);
        } else {
            // パスワードが空なら、更新対象から除外
            userEntity.setPassword(null); // 更新SQLで無視されるように設計
        }
        userMapper.update(userEntity);
    }

    @Override
    public void softDelete(Long id) {
        userMapper.softDelete(id); // 論理削除（deleted = true）
    }

}
