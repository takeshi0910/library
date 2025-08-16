package com.takeshi.library;

import com.takeshi.library.utill.ConsoleEncodingUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@MapperScan("com.takeshi.library.mapper") // 記載することでエラー詳細が表面化する。
public class LibraryApplication {
	public static void main(String[] args) {
		ConsoleEncodingUtil.initUtf8();
		SpringApplication.run(LibraryApplication.class, args);
	}
}
