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
        this.bookService = bookService;
        this.genreMapper = genreMapper;
    }

    @GetMapping
    public String list(Model model) {
        List<Book> books = bookService.searchBooks(""); // 空文字で検索 → 全件表示
        model.addAttribute("searchBookForm", new SearchBookForm());
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        Book book = (id != null) ? bookService.findById(id) : new Book();
        model.addAttribute("book", book);
        model.addAttribute("isEdit", id != null);

        List<Genre> genres = genreMapper.findAllActive();
        model.addAttribute("genres", genres);

        return "books/form"; // HTMLファイルは form.html に統一
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Validated Book book, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genres", genreMapper.findAllActive()); // 再表示のためジャンル一覧セット
            return "books/form"; // エラー時はフォームに戻る
        }
        bookService.insert(book);
        redirectAttributes.addFlashAttribute("message",
                "『" + book.getTitle() + "』を登録しました。");
        return "redirect:/books"; // 一覧画面へリダイレクト
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Validated Book book, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genres", genreMapper.findAllActive());
            return "books/form";
        }
        bookService.update(book);
        redirectAttributes.addFlashAttribute("message",
                "『" + book.getTitle() + "』を更新しました。");
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // 削除前にタイトルを取得してメッセージ用に使う（任意）
        Book book =bookService.findById(id);
        String title = (book != null) ? book.getTitle() : "未確認の書籍";

        bookService.softDelete(id); // 実際の削除処理
        redirectAttributes.addFlashAttribute("message", "『" + title + "』を削除しました。");

        return "redirect:/books"; // 一覧に戻る
    }

}