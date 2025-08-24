package com.takeshi.library.utill;

import com.takeshi.library.dto.PageRequestDto;
import com.takeshi.library.form.SearchBookForm;

/**
 * DataTables形式の検索フォームからページング情報（ページ番号・サイズ・ソート条件）を抽出し、
 * PageRequestオブジェクトへ変換するユーティリティクラス。<br>
 *
 * ControllerからService層へのページング情報の受け渡しに使用する。
 */
public final class PageRequestConverter {
    // インスタンス化防止
    private PageRequestConverter() {}

    public static PageRequestDto toPageRequest(SearchBookForm form) {
        int pageNum = form.getStart() / form.getLength() + 1; // ページ番号
        int pageSize = form.getLength(); // ページ数

        // ソートについては条件があればそれを指定。未指定なら MyBatis 側でフォールバックするため null で初期化。
        String sortBy = null;
        Boolean isDescending = null;

        if (form.getOrder() != null && !form.getOrder().isEmpty()) {
            SearchBookForm.Order order = form.getOrder().get(0);
            int columnIndex = order.getColumn();
            isDescending = "desc".equalsIgnoreCase(order.getDir());

            if (form.getColumns() != null && columnIndex < form.getColumns().size()) {
                sortBy = form.getColumns().get(columnIndex).getData();
                System.out.println("sortBy:" + sortBy);
            }
        }

        return new PageRequestDto(pageNum, pageSize, sortBy, isDescending);
    }
}
