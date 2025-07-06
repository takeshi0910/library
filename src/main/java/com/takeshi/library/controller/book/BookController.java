package com.takeshi.library.controller.book;


import com.takeshi.library.model.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

   /* private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 📘 一覧表示
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books/list"; // ← books/list.html を表示（まだ作成してない場合は後ほど）
    }*/

    // 🆕 新規登録フォーム表示
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("genres", List.of("小説", "ビジネス", "歴史", "その他"));
        return "books/new"; // ← 拡張子 .html は書かない！
    }


    // 📥 登録処理（POST）
    /*@PostMapping
    public String createBook(@ModelAttribute @Valid Book book,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genres", List.of("小説", "ビジネス", "歴史", "その他"));
            return "books/new";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }*/

    // 追加予定の構想（必要に応じて）
    // - 編集フォーム（GET /books/{id}/edit）
    // - 編集処理（POST /books/{id}）
    // - 削除処理（POST /books/{id}/delete）

}