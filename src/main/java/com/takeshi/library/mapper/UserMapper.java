package com.takeshi.library.mapper;

import com.takeshi.library.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll(); // deleted = false の全件取得

    List<User> findByKeyword(String keyword); // 名前などで部分一致検索

    User findById(Long id); // IDで取得

    User findByEmail(String email);

    void insert(User user); // 新規登録

    void update(User user); // 更新

    void softDelete(Long id); // 論理削除（deleted = true に更新）
}