package com.takeshi.library.validation;

import com.takeshi.library.form.UserForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserForm> {

    @Override
    public boolean isValid(UserForm form, ConstraintValidatorContext context) {
        if (form.getPassword() == null || form.getConfirmPassword() == null) {
            return false;
        }
        return form.getPassword().equals(form.getConfirmPassword());
    }
}
