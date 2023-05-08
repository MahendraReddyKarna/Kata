package com.bnppf.kata.controller;

import com.bnppf.kata.dto.Book;
import com.bnppf.kata.service.BasketService;
import com.bnppf.kata.service.BasketService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShoppingBasketController {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingBasketController.class);
    private final BasketService basketService = new BasketService();

    @PostMapping("/calculate-price")
    public Map<String,Object> calculatePrice(@RequestBody Book[] books) {
        Map<String,Object> result = new HashMap<>();
        List<String> booksList = new ArrayList<>();
        for(Book book : books) {
            booksList.add(book.getTitle());
        }
        final int [] bookCounts = basketService.addBooks(booksList);
        double price =  basketService.calculatePrice(bookCounts);
        result.put("totalPrice",price);
        logger.info("response "+ result);
        return result;
    }
}
