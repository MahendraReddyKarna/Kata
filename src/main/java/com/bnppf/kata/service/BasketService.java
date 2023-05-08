package com.bnppf.kata.service;

import com.bnppf.kata.dto.Book;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

  private static final Logger logger = LoggerFactory.getLogger(BasketService.class);

  private static final double BOOK_PRICE = 50.0;
  private final double[] discounts = {1, 1, 0.95, 0.9, 0.8, 0.75};

  public int[] addBooks(List<String> titles) {
    final int[] bookCounts = new int[5];
    for (String title : titles) {
      int index = getTitleIndex(title);
      if (index >= 0) {
        bookCounts[index]++;
      }
    }
    return bookCounts;
  }


  public double calculatePrice(int[] bookCounts) {
    double price = 0.0;
    if (bookCounts.length > 0) {
      price = calculateOptimalPrice(bookCounts);
    }
    return price;
  }

  /*
  Calculating the price in recursive way
   */
  private double calculateOptimalPrice(int[] bookCounts) {
    //Get Unique books
    int uniqueBooks = countUniqueBooks(bookCounts);

    if (uniqueBooks == 0) {
      return 0;
    }

    double minPrice = Double.MAX_VALUE;
    for (int i = uniqueBooks; i > 0; i--) {
      int[] newBookCounts = Arrays.copyOf(bookCounts, bookCounts.length);
      decreaseBookCounts(newBookCounts, i);
      double totalPrice = i * BOOK_PRICE * discounts[i] + calculateOptimalPrice(newBookCounts);
      minPrice = Math.min(minPrice, totalPrice);
    }
    logger.info(" books count " + uniqueBooks + " price " + minPrice);
    return minPrice;
  }

  private int countUniqueBooks(int[] bookCounts) {
    int count = 0;
    for (int bookCount : bookCounts) {
      if (bookCount > 0) {
        count++;
      }
    }
    return count;
  }

  private void decreaseBookCounts(int[] bookCounts, int booksToDecrease) {
    int decreasedBooks = 0;
    for (int i = 0; i < bookCounts.length && decreasedBooks < booksToDecrease; i++) {
      if (bookCounts[i] > 0) {
        bookCounts[i]--;
        decreasedBooks++;
      }
    }
  }

  private int getTitleIndex(String title) {
    switch (title) {
      case "Clean Code":
        return 0;
      case "Clean Coder":
        return 1;
      case "Clean Architecture":
        return 2;
      case "Test Driven Development by Example":
        return 3;
      case "Working Effectively With Legacy Code":
        return 4;
      default:
        return -1;
    }
  }

}
