package com.takeshi.library.controller.api;

import com.takeshi.library.dto.PageRequestDto;
import com.takeshi.library.entity.BookEntity;
import com.takeshi.library.form.SearchBookForm;
import com.takeshi.library.service.BookService;
import com.takeshi.library.utill.PageRequestConverter;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;
    /**
     * DataTables連携用の検索処理（POST）
     */
    @PostMapping("/search")
    public Map<String, Object> search(@RequestBody SearchBookForm form) {
        // SearchBookForm->Dto詰め替え
        PageRequestDto pageRequestDto = PageRequestConverter.toPageRequest(form);
        String keyword = form.getKeyword();
        PageInfo<BookEntity> pageInfo = bookService.searchBooks(keyword, pageRequestDto);

        Map<String, Object> response = new HashMap<>();
        response.put("draw", form.getDraw()); // DataTablesから送られてくるdrawをそのまま返す
        response.put("recordsTotal", pageInfo.getTotal()); // 全件数
        response.put("recordsFiltered", pageInfo.getTotal()); // 検索後の件数（フィルターしてないなら同じ）
        response.put("data", pageInfo.getList()); // 実データ

        return response;
    }

}
