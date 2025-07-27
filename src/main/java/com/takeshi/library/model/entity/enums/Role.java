package com.takeshi.library.model.entity.enums;

public enum Role {
    USER("一般ユーザー"),
    ADMIN("管理者");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
