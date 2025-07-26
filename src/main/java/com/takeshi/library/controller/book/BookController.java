package com.takeshi.library.controller.book;


import com.takeshi.library.form.SearchBookForm;
import com.takeshi.library.mapper.BookMapper;
import com.takeshi.library.model.entity.Book;
import com.takeshi.library.model.entity.Genre;
import com.takeshi.library.service.BookService;
import com.takeshi.library.service.GenreService;
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
    private final GenreService genreService;

    @Autowired
    public BookController(BookService bookService, GenreService genreService) {
        this.bookService = bookService;
        this.genreService = genreService;
    }

    // 一覧表示
    @GetMapping
    public String list(Model model) {
        List<Book> books = bookService.searchBooks(""); // 空文字で検索 → 全件表示
        model.addAttribute("searchBookForm", new SearchBookForm());
        model.addAttribute("books", books);
        return "books/list";
    }

    // 登録画面表示
    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        Book book = (id != null) ? bookService.findById(id) : new Book();
        model.addAttribute("book", book);
        model.addAttribute("isEdit", id != null);

        List<Genre> genres = genreService.findAllActive();
        model.addAttribute("genres", genres);

        return "books/form"; // HTMLファイルは form.html に統一
    }

    // 登録・更新処理
    @PostMapping("/save")
    public String save(@ModelAttribute @Validated Book book, BindingResult result,
                       RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genres", genreService.findAllActive());
            return "books/form";
        }

        if (book.getId() == null) {
            bookService.insert(book);
            redirectAttributes.addFlashAttribute("message",
                    "『" + book.getTitle() + "』を登録しました。");
        } else {
            bookService.update(book);
            redirectAttributes.addFlashAttribute("message",
                    "『" + book.getTitle() + "』を更新しました。");
        }

        return "redirect:/books";
    }

    // 削除処理
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // 削除前にタイトルを取得してメッセージ用に使う（任意）
        Book book = bookService.findById(id);
        String title = (book != null) ? book.getTitle() : "未確認の書籍";

        bookService.softDelete(id); // 実際の削除処理
        redirectAttributes.addFlashAttribute("message", "『" + title + "』を削除しました。");

        return "redirect:/books"; // 一覧に戻る
    }
}