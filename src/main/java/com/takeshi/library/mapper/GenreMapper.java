package com.takeshi.library.mapper;

import com.takeshi.library.model.entity.Genre;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    List<Genre> findAllActive(); // deleted = false だけ取得
}

