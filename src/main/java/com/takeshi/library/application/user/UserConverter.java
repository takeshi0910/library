package com.takeshi.library.application.user;

import com.takeshi.library.entity.UserEntity;
import com.takeshi.library.form.UserForm;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static UserEntity toEntity(UserForm form) {
        UserEntity entity = new UserEntity();
        entity.setId(form.getId());
        entity.setName(form.getName());
        entity.setPassword(form.getPassword());
        entity.setEmail(form.getEmail());
        entity.setRole(form.getRole());
        return entity;
    }

    public static UserForm toForm(UserEntity entity) {
        UserForm form = new UserForm();
        form.setId(entity.getId());
        form.setName(entity.getName());
        form.setEmail(entity.getEmail());
        form.setRole(entity.getRole());
        return form;
    }
}