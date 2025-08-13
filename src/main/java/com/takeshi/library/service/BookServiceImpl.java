package com.takeshi.library.service;

import com.takeshi.library.application.book.BookConverter;
import com.takeshi.library.entity.BookEntity;
import com.takeshi.library.mapper.BookMapper;
import com.takeshi.library.form.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookConverter bookConverter;

    @Override
    public List<BookEntity> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return bookMapper.searchBooks(""); // 全件表示（XMLの中で条件分岐）
        }
        return bookMapper.searchBooks(keyword);
    }

    @Override
    public void insert(BookForm book){
        BookEntity bookEntity = bookConverter.toEntity(book);
        bookMapper.insert(bookEntity);
    }

    @Override
    public BookEntity findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public void update(BookForm book) {
        BookEntity bookEntity = bookConverter.toEntity(book);
        bookMapper.update(bookEntity);
    }

    @Override
    public void softDelete(Integer id) {
        bookMapper.softDelete(id);
    }
}
