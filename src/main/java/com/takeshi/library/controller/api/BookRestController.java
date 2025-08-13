package com.takeshi.library.controller.api;

import com.takeshi.library.entity.BookEntity;
import com.takeshi.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    // 全件取得
    @GetMapping
    public List<BookEntity> list() {
        return bookService.searchBooks(""); // 空文字で全件取得
    }

    // キーワード検索
    @GetMapping("/search")
    public List<BookEntity> search(@RequestParam String keyword) {
        return bookService.searchBooks(keyword);
    }
}
