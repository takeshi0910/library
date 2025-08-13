package com.takeshi.library.entity;

import lombok.Data;

@Data
public class GenreEntity {
    private Integer id;
    private String name;
    private Boolean deleted = false; // 初期値を明示
}
