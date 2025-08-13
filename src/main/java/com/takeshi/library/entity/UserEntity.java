package com.takeshi.library.entity;

import com.takeshi.library.form.UserForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = "password")
public class UserEntity {

    private Integer id;
    private String name;
    private String password;
    private String email;
    private Role role = Role.USER;   // 明示的に初期値を設定
    private Boolean deleted = false;
}
