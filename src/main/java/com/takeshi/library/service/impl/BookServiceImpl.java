package com.takeshi.library.service.impl;

import com.takeshi.library.mapper.BookMapper;
import com.takeshi.library.model.entity.Book;
import com.takeshi.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return bookMapper.searchBooks(""); // 全件表示（XMLの中で条件分岐）
        }
        return bookMapper.searchBooks(keyword);
    }

    @Override
    public void insert(Book book){
        bookMapper.insert(book);
    }

    @Override
    public Book findById(Long id) {
        return bookMapper.findById(id);
    }

    @Override
    public void update(Book book) {

    }
}
