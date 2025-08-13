package com.takeshi.library.validation;

import com.takeshi.library.form.UserForm;
import com.takeshi.library.mapper.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, UserForm> {

    private final UserMapper userMapper;

    @Override
    public boolean isValid(UserForm form, ConstraintValidatorContext context) {
        if (form.getEmail() == null || form.getEmail().isBlank()) return true;
        return !userMapper.existsByEmail(form.getEmail(), form.getId());
    }

}
