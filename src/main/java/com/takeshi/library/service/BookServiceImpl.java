package com.takeshi.library.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.takeshi.library.application.book.BookConverter;
import com.takeshi.library.dto.PageRequestDto;
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
    public PageInfo<BookEntity> searchBooks(String keyword, PageRequestDto pageRequestDto) {

        // PageHelperでページング開始（ページ番号は1から始まる）
        PageHelper.startPage(pageRequestDto.getPageNum(), pageRequestDto.getPageSize());

        // キーワードが空なら全件検索
        String searchKeyword = (keyword == null || keyword.trim().isEmpty()) ? "" : keyword;

        // Mapperから検索結果を取得し、PageInfoにラップ
        String sortBy = pageRequestDto.getSortBy();
        Boolean isDescending = pageRequestDto.isDescending();
        List<BookEntity> books = bookMapper.searchBooks(searchKeyword, sortBy, isDescending);
        return new PageInfo<>(books);

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
