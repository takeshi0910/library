package com.takeshi.library.form;

import com.takeshi.library.entity.Role;
import com.takeshi.library.validation.UniqueEmail;
import com.takeshi.library.validation.group.ValidGroup1;
import com.takeshi.library.validation.group.ValidGroup2;
import com.takeshi.library.validation.PasswordPolicy;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@UniqueEmail(groups = ValidGroup2.class)
public class UserForm {

    private Integer id;

    @NotBlank(message = "ユーザー名は必須です", groups = ValidGroup1.class)
    private String name;

    @PasswordPolicy
    private String password;

    private String confirmPassword;

    @NotBlank(message = "メールアドレスは必須です", groups = ValidGroup1.class)
    @Email(message = "メールアドレスの形式が正しくありません", groups = ValidGroup2.class)
    private String email;

    // private String role = "USER";   // 明示的に初期値を設定
    @NotNull(message = "権限は必須です", groups = ValidGroup1.class)
    private Role role = Role.USER;   // 明示的に初期値を設定

    @AssertTrue(message = "パスワードは必須です", groups = ValidGroup1.class)
    public boolean isPasswordValid() {
        // 更新時（idあり）ならパスワード空でもOK
        if (id != null) {
            return true;
        }

        // 登録時（idなし）ならパスワード必須
        return password != null && !password.isBlank();
    }

    @AssertTrue(message = "パスワードと確認用パスワードが一致しません", groups = ValidGroup2.class)
    public boolean isPasswordConfirmed() {
        if (password == null || confirmPassword == null) return true; // 空欄時は別バリデーションで処理
        return password.equals(confirmPassword);
    }
}