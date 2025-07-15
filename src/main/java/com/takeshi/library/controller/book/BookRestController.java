package com.takeshi.library.controller.book;

import com.takeshi.library.model.entity.Book;
import com.takeshi.library.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    // 全件取得
    @GetMapping
    public List<Book> list() {
        return bookService.searchBooks(""); // 空文字で全件取得
    }

    // キーワード検索
    @GetMapping("/search")
    public List<Book> search(@RequestParam String keyword) {
        return bookService.searchBooks(keyword);
    }
}
