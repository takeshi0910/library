package com.takeshi.library.form;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
