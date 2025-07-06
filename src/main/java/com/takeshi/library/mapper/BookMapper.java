package com.takeshi.library.mapper;


import com.takeshi.library.model.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> findAll();
    void insert(Book book);
}