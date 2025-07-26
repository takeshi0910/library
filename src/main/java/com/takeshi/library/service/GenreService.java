package com.takeshi.library.service;

import com.takeshi.library.model.entity.Book;
import com.takeshi.library.model.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAllActive();
}
