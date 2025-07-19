package com.takeshi.library.service;

import com.takeshi.library.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> searchBooks(String keyword);
    void insert(Book book);
    Book findById(Long id); // ← 編集用の取得メソッドを追加！
    void update(Book book); // ← 更新用のメソッドも追加できる！
    void softDelete(Long id);
}
