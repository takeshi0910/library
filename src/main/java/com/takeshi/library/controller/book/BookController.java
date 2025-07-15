package com.takeshi.library.controller.book;


import com.takeshi.library.form.SearchBookForm;
import com.takeshi.library.mapper.BookMapper;
import com.takeshi.library.mapper.GenreMapper;
import com.takeshi.library.model.entity.Book;
import com.takeshi.library.model.entity.Genre;
import com.takeshi.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final GenreMapper genreMapper;

    @Autowired
    public BookController(BookService bookService, GenreMapper genreMapper) {
        this.bookService = bookService ;
        this.genreMapper = genreMapper;
    }

    @GetMapping
    public String list(Model model) {
        List<Book> books = bookService.searchBooks(""); // 空文字で検索 → 全件表示
        model.addAttribute("searchBookForm", new SearchBookForm());
        model.addAttribute("books", books);
        return "books/list";
    }

    // 🆕 新規登録フォーム表示
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        List<Genre> genres = genreMapper.findAllActive();
        model.addAttribute("genres", genres);
        return "books/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute @Validated Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genres", genreMapper.findAllActive()); // 再表示のためジャンル一覧セット
            return "books/new"; // エラー時はフォームに戻る
        }
        bookService.insert(book);
        return "redirect:/books"; // 一覧画面へリダイレクト
    }

}