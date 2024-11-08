package com.store.book.entity;

import com.store.book.dto.response.RespBookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private Long categoryId;
    private String publisher;
    private String author;
    private LocalDateTime registerDate;
    private String bookName;
    private int unitPrice;
    private int discountRate;

    private String categoryName;
}
