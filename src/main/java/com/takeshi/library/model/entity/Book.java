package com.takeshi.library.model.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class Book {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull(message = "ジャンルを選択してください")
    private Integer genreId;

    private String genreName;

    private String isbn;

    private boolean deleted;

}
