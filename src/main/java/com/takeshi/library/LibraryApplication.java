package com.takeshi.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.takeshi.library.mapper") // 記載することでエラー詳細が表面化する。
public class LibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
