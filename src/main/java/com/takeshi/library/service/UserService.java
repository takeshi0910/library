package com.takeshi.library.service;

import com.takeshi.library.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> searchUsers(String keyword);       // 一覧・検索
    User findById(Long id);                       // IDで取得（編集・削除用）
    void insert(User user);                       // 新規登録
    void update(User user);                       // 更新
    void softDelete(Long id);                     // 論理削除
    User findByEmail(String name);
}
