package com.takeshi.library.form;

import com.takeshi.library.validation.PasswordMatches;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@PasswordMatches
public class UserForm {
    @NotBlank(message = "ユーザー名は必須です")
    private String name;

    @NotBlank(message = "パスワードは必須です")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "パスワードは半角英数8文字以上で、大文字・小文字・数字を含めてください")
    private String password;

    @NotBlank(message = "確認用パスワードは必須です")
    private String confirmPassword;

    @Email(message = "メールアドレスの形式が正しくありません")
    private String mail;

}