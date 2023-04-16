package com.example.demo.book_detail.data;

import java.util.Arrays;
import java.util.List;

public record Book(String id, String name, int pageCount, String authorId) {
    private static List<Book> bookList = Arrays.asList(
            new Book("book1", "Effective Java", 210, "author1"),
            new Book("book2", "Java本格入門", 400, "author2"),
            new Book("book3", "マイクロフロントエンド", 300, "author3")
    );

    public static Book getById(String id) {
        return bookList.stream().filter(book -> book.id.equals(id)).findFirst().orElse(null);
    }
}
