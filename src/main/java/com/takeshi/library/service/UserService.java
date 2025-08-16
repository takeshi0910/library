package com.takeshi.library.service;

import com.takeshi.library.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> searchUsers(String keyword);       // 一覧・検索
    UserEntity findById(Integer id);                       // IDで取得（編集・削除用）
    void insert(UserEntity userEntity);                       // 新規登録
    void update(UserEntity userEntity);                       // 更新
    void softDelete(Integer id);                     // 論理削除
    UserEntity findByEmail(String email);
}
