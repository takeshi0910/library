package com.takeshi.library.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordPolicyValidator implements ConstraintValidator<PasswordPolicy, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) return false;

        context.disableDefaultConstraintViolation();

        if (!password.matches("^[a-zA-Z\\d]+$")) {
            context.buildConstraintViolationWithTemplate("パスワードは半角英数字のみで構成してください")
                    .addConstraintViolation();
            return false;
        }

        if (password.length() < 8) {
            context.buildConstraintViolationWithTemplate("パスワードは8文字以上にしてください")
                    .addConstraintViolation();
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            context.buildConstraintViolationWithTemplate("パスワードには英字の大文字を1文字以上含めてください")
                    .addConstraintViolation();
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            context.buildConstraintViolationWithTemplate("パスワードには英字の小文字を1文字以上含めてください")
                    .addConstraintViolation();
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            context.buildConstraintViolationWithTemplate("パスワードには数字を1文字以上含めてください")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
