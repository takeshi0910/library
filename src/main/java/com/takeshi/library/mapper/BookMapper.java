package com.takeshi.library.mapper;

import com.takeshi.library.model.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> searchBooks(String keyword);

    void insert(Book book);

    void update(Book book);

    Book findById(Long id); // これを追加！

    void softDelete(Long id);
}