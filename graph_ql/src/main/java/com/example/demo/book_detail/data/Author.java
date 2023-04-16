package com.example.demo.book_detail.data;

import java.util.Arrays;
import java.util.List;

public record Author(String id, String firstName, String lastName) {
    private static List<Author> authorList = Arrays.asList(
            new Author("author1", "aaa", "bbb"),
            new Author("author2", "ccc", "ddd"),
            new Author("author3", "eee", "fff")
    );

    public static Author getById(String id) {
        return authorList.stream().filter(author -> author.id.equals(id)).findFirst().orElse(null);
    }
}
