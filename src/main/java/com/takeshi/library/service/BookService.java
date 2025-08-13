package com.takeshi.library.service;

import com.takeshi.library.entity.BookEntity;
import com.takeshi.library.form.BookForm;

import java.util.List;

public interface BookService {
    List<BookEntity> searchBooks(String keyword);
    void insert(BookForm book);
    BookEntity findById(Integer id);
    void update(BookForm book);
    void softDelete(Integer id);
}
