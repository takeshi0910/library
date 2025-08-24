package com.takeshi.library.service;

import com.github.pagehelper.PageInfo;
import com.takeshi.library.dto.PageRequestDto;
import com.takeshi.library.entity.BookEntity;
import com.takeshi.library.form.BookForm;

public interface BookService {
    PageInfo<BookEntity> searchBooks(String keyword, PageRequestDto pageRequestDto);
    void insert(BookForm book);
    BookEntity findById(Integer id);
    void update(BookForm book);
    void softDelete(Integer id);
}
