package com.takeshi.library.mapper;

import com.takeshi.library.entity.GenreEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    List<GenreEntity> findAll(); // deleted = false だけ取得
}

