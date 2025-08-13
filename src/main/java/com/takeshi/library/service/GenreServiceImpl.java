package com.takeshi.library.service;

import com.takeshi.library.entity.GenreEntity;
import com.takeshi.library.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;

    @Override
    public List<GenreEntity> findAll() {
        return genreMapper.findAll();
    }
}
