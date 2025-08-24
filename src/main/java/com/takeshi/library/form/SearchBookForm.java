package com.takeshi.library.form;

import lombok.Data;

import java.util.List;

/** 書籍検索における検索条件とDataTablesのソート・ページング情報を受け取るフォームクラス。*/
@Data
public class SearchBookForm {
    //　検索条件
    private String keyword = "";

    //　以下、DataTablesのソート・ページング情報
    private int draw;              // DataTablesの描画回数（レスポンスにそのまま返す）
    private int start;             // 開始位置（ページング用）
    private int length;            // 1ページの件数
    private List<Order> order;     // ソート条件
    private List<Column> columns;  // カラム情報

    /** ソート条件を保持する内部クラス。 */
    @Data
    public static class Order {
        private int column;
        private String dir; // "asc" or "desc"
    }

    /** カラム情報を保持する内部クラス。 */
    @Data
    public static class Column {
        private String data;
        private String name;
        private boolean searchable;
        private boolean orderable;
    }
}