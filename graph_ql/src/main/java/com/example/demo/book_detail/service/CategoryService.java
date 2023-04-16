package com.example.demo.book_detail.service;

import com.example.demo.book_detail.data.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    private final List<Category> categoryList;

    public CategoryService() {
        this.categoryList = new ArrayList<>(
                Arrays.asList(new Category(1, "焼肉"), new Category(2, "イタリアン"))
        );
    }

    public Category getCategoryById(Integer id) {
        return categoryList.stream()
                .filter(category -> category.id().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
