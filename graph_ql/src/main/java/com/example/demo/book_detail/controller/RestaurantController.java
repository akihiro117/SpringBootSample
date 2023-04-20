package com.example.demo.book_detail.controller;

import com.example.demo.book_detail.data.*;
import com.example.demo.book_detail.service.CategoryService;
import com.example.demo.book_detail.service.RestaurantService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final CategoryService categoryService;

    public RestaurantController(RestaurantService restaurantService,
                                CategoryService categoryService) {
        this.restaurantService = restaurantService;
        this.categoryService = categoryService;
    }

    /**
     * このメソッド名と schema.graphqls の Queryの名前を揃える必要がある。
     */
    @QueryMapping
    public List<Restaurant> allRestaurants() {
        return restaurantService.getRestaurantList();
    }

    @QueryMapping
    public Restaurant restaurantDetail(@Argument Integer id) {
        return restaurantService.getRestaurantById(id);
    }

    /**
     * このメソッド名とschema.graphqlsのマッピング先の変数名を揃える必要がある。
     */
    @SchemaMapping
    public Category category(Restaurant restaurant) {
        return categoryService.getCategoryById(restaurant.categoryId());
    }
}
