package com.takeshi.library.model.entity;

import lombok.Data;

@Data
public class Genre {
    private Integer id;
    private String name;
    private Boolean deleted; // 論理削除用
}
