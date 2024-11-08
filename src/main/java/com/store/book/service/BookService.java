package com.store.book.service;

import com.store.book.dto.request.ReqSearchOptionsDto;
import com.store.book.dto.response.RespBookDto;
import com.store.book.repository.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    public RespBookDto getBooksByOptions(ReqSearchOptionsDto dto) {
        System.out.println(bookMapper.getBooksByOption(dto.getSort(), dto.getSort()));
        return RespBookDto.builder()
                .books(bookMapper.getBooksByOption(dto.getSort(), dto.getSort()))
                .build();
    }
}
