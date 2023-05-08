package com.bnppf.kata;

import com.bnppf.kata.controller.ShoppingBasketController;
import com.bnppf.kata.dto.Book;
import com.bnppf.kata.service.BasketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ShoppingBasketController.class)
public class ShoppingBasketControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingBasketControllerTest.class);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @Test
    public void testCalculatePriceEndpoint() throws Exception {
        Book[] books = {
                new Book("Clean Code", 50),
                new Book("Clean Coder", 50),
                new Book("Clean Architecture", 50),
                new Book("Test Driven Development by Example", 50),
                new Book("Working Effectively With Legacy Code", 50)

        };
        String payLoad = mapper.writeValueAsString(books);
        logger.info(payLoad);
        mockMvc.perform(post("/calculate-price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(187.5));

    }

    @Test
    public void testCalculateThreePriceEndpoint() throws Exception {
        Book[] books = {
                new Book("Clean Code", 50),
                new Book("Clean Coder", 50),
                new Book("Clean Architecture", 50)
        };
        String payLoad = mapper.writeValueAsString(books);
        logger.info(payLoad);
        mockMvc.perform(post("/calculate-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(135.0));
    }

    @Test
    public void testCalculateOnePriceEndpoint() throws Exception {
        Book[] books = {
                new Book("Clean Code", 50)

        };
        String payLoad = mapper.writeValueAsString(books);
        logger.info(payLoad);
        mockMvc.perform(post("/calculate-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(50.0));
    }

}
