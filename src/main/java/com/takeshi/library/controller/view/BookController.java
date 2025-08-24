package com.takeshi.library.controller.view;

import com.takeshi.library.application.book.BookConverter;
import com.takeshi.library.dto.PageRequestDto;
import com.takeshi.library.entity.BookEntity;
import com.takeshi.library.form.BookForm;
import com.takeshi.library.entity.GenreEntity;
import com.takeshi.library.form.SearchBookForm;
import com.takeshi.library.service.BookService;
import com.takeshi.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final GenreService genreService;

    @ModelAttribute("genres")
    public List<GenreEntity> populateGenres() {
        return genreService.findAll();
    }

    // 一覧表示
    @GetMapping
    public String list(Model model) {
        model.addAttribute("searchBookForm", new SearchBookForm());
        return "books/list";
    }

    // 登録画面表示
    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Integer id, Model model) {
        BookEntity bookEntity = (id != null) ? bookService.findById(id) : new BookEntity();
        BookForm bookForm = BookConverter.toForm(bookEntity);
        model.addAttribute("bookForm", bookForm);
        return "books/form"; // HTMLファイルは form.html に統一
    }

    // 登録・更新処理
    @PostMapping("/save")
    public String save(@ModelAttribute @Validated BookForm book, BindingResult result,
                       RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
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
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // 削除前にタイトルを取得してメッセージ用に使う（任意）
        BookEntity bookEntity = bookService.findById(id);
        String title = (bookEntity != null) ? bookEntity.getTitle() : "未確認の書籍";

        bookService.softDelete(id); // 実際の削除処理
        redirectAttributes.addFlashAttribute("message", "『" + title + "』を削除しました。");

        return "redirect:/books"; // 一覧に戻る
    }
}