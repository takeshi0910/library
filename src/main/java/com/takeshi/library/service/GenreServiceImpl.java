package com.takeshi.library.service;

import com.takeshi.library.mapper.GenreMapper;
import com.takeshi.library.model.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    public List<Genre> findAll() {
        return genreMapper.findAll();
    }
}
