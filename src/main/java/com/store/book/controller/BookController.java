package com.store.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/book")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body("정상응답");
    }
}
