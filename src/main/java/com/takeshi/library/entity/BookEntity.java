package com.takeshi.library.entity;

import lombok.Data;

@Data
public class BookEntity {

    private Integer id;

    private String title;

    private String author;

    private Integer genreId;

    private String genreName; // genreId に紐づくジャンル名（JOIN結果などで取得）

    private String isbn;

    private boolean deleted;
}

