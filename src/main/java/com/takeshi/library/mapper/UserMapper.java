package com.takeshi.library.mapper;

import com.takeshi.library.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> findAll(); // deleted = false の全件取得

    List<UserEntity> findByKeyword(String keyword); // 名前などで部分一致検索

    UserEntity findById(Long id); // IDで取得

    UserEntity findByEmail(String email);

    void insert(UserEntity userEntity); // 新規登録

    void update(UserEntity userEntity); // 更新

    void softDelete(Long id); // 論理削除（deleted = true に更新）

    boolean existsByEmail(@Param("email") String email, @Param("id") Integer id);


}