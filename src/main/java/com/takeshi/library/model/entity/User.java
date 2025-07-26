package com.takeshi.library.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = "password")
public class User {
    public User(Long id, String name, String password, String mail) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.role = "USER";         // デフォルトロール
        this.deleted = false;       // 削除フラグOFF
    }

    private Long id;
    private String name;
    private String password;
    private String mail;
    private String role = "USER";   // 明示的に初期値を設定
    private Boolean deleted = false;
}
