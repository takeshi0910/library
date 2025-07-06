package com.takeshi.library.model.entity;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class Book {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String genre;

    private String isbn;

    private boolean deleted;

}
