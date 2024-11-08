package com.store.book.repository;

import com.store.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> getBooksByOption(
            @Param("category") String category,
            @Param("sort") String sort);
}
