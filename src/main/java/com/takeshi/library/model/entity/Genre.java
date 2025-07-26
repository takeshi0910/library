package com.takeshi.library.model.entity;

import lombok.Data;

@Data
public class Genre {
    private Integer id;
    private String name;
    private Boolean deleted = false; // 初期値を明示
}
