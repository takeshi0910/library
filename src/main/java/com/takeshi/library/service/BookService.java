package com.takeshi.library.service;

import com.takeshi.library.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> searchBooks(String keyword);
    void insert(Book book);
    Book findById(Long id);
    void update(Book book);
    void softDelete(Long id);
}
