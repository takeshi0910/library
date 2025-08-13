package com.takeshi.library.domain.auth;

public enum AuthResult {
    SUCCESS("ログイン成功"),
    EMAIL_NOT_FOUND("そのメールアドレスは登録されていません"),
    INVALID_PASSWORD("パスワードが間違っています");

    private final String message;

    AuthResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


