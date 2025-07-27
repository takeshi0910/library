package com.takeshi.library.model.entity;

import com.takeshi.library.form.UserForm;
import com.takeshi.library.model.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = "password")
public class User {

    public User(Long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.deleted = false;
    }

    public User(UserForm form) {
        this.name = form.getName();
        this.email = form.getEmail();
        this.password = form.getPassword();
        this.role = form.getRole();
        this.deleted = false;
    }

    private Long id;
    private String name;
    private String password;
    private String email;
    private Role role = Role.USER;   // 明示的に初期値を設定
    private Boolean deleted = false;
}
