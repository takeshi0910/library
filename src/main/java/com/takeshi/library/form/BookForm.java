package com.takeshi.library.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class BookForm {

    private Integer id;

    @NotBlank(message = "書名は必須項目です")
    private String title;

    @NotBlank(message = "著者は必須項目です")
    private String author;

    @NotNull(message = "ジャンルを選択してね")
    private Integer genreId;

    private String genreName;

    private String isbn;

    private boolean deleted = false; // 初期値を明示
}
