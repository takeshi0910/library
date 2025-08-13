package com.takeshi.library.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordPolicyValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordPolicy {
    String message() default "パスワードの形式が正しくありません";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}