package com.takeshi.library.service;

import com.takeshi.library.entity.GenreEntity;

import java.util.List;

public interface GenreService {
    List<GenreEntity> findAll();
}
