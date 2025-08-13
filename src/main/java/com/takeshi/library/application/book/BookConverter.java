package com.takeshi.library.application.book;

import com.takeshi.library.entity.BookEntity;
import com.takeshi.library.form.BookForm;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    public static BookEntity toEntity(BookForm form) {
        BookEntity entity = new BookEntity();
        entity.setId(form.getId());
        entity.setTitle(form.getTitle());
        entity.setAuthor(form.getAuthor());
        entity.setGenreId(form.getGenreId());
        entity.setGenreName(form.getGenreName());
        entity.setIsbn(form.getIsbn());
        entity.setDeleted(form.isDeleted());
        return entity;
    }

    public static BookForm toForm(BookEntity entity) {
        BookForm form = new BookForm();
        form.setId(entity.getId());
        form.setTitle(entity.getTitle());
        form.setAuthor(entity.getAuthor());
        form.setGenreId(entity.getGenreId());
        form.setGenreName(entity.getGenreName());
        form.setIsbn(entity.getIsbn());
        form.setDeleted(entity.isDeleted());
        return form;
    }
}