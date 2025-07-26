package com.takeshi.library.service.impl;

import com.takeshi.library.mapper.BookMapper;
import com.takeshi.library.mapper.GenreMapper;
import com.takeshi.library.model.entity.Genre;
import com.takeshi.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    public List<Genre> findAllActive() {
        return genreMapper.findAllActive();
    }
}
