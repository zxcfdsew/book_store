package com.store.book.entity;

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
    private Integer unitPrice;
    private Integer discountRate;
}
