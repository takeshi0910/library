package com.takeshi.library.mapper;

import com.takeshi.library.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<BookEntity> searchBooks(String keyword);

    void insert(BookEntity book);

    void update(BookEntity book);

    BookEntity findById(Integer id);

    void softDelete(Integer id);
}