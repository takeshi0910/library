package com.takeshi.library.dto;

import lombok.Data;

/**
 * DataTablesから送信されたページング・ソート情報を保持するDTO。
 */
@Data
public class PageRequestDto {
    private int pageNum = 1;
    private int pageSize = 10;
    private String sortBy;
    private boolean isDescending;

    // 引数なしコンストラクタ（初期化用）
    public PageRequestDto() {
    }

    public PageRequestDto(int pageNum, int pageSize, String sortBy, boolean isDescending) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.isDescending = isDescending;
    }
}