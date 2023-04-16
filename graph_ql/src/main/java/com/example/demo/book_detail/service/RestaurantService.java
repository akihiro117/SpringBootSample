package com.example.demo.book_detail.service;

import com.example.demo.book_detail.data.Address;
import com.example.demo.book_detail.data.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RestaurantService {
    private final List<Restaurant> restaurantList;

    public RestaurantService() {
        this.restaurantList = new ArrayList<>(
                Arrays.asList(new Restaurant(10, "レストラン1", new Address("Tokyo", "Minato-Ku"), 1),
                        new Restaurant(20, "レストラン2", new Address("Kanagawa", "Yokohama"), 2))
        );
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public Restaurant getRestaurantById(Integer id) {
        return restaurantList.stream()
                .filter(restaurant -> restaurant.id().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
