package com.store.book.controller;

import com.store.book.dto.request.ReqSearchOptionsDto;
import com.store.book.dto.response.RespBookDto;
import com.store.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getBooksByOptions(ReqSearchOptionsDto dto) {
        return ResponseEntity.ok().body(bookService.getBooksByOptions(dto));
    }
}
