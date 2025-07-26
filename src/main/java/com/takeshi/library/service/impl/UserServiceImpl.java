package com.takeshi.library.service.impl;

import com.takeshi.library.mapper.UserMapper;
import com.takeshi.library.model.entity.User;
import com.takeshi.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> searchUsers(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return userMapper.findAllActive(); // 削除されてない全件
        }
        return userMapper.findByKeyword(keyword);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByMail(String mail) {
        return userMapper.findByMail(mail);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void softDelete(Long id) {
        userMapper.softDelete(id); // 論理削除（deleted = true）
    }

}
