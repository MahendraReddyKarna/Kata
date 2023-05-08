package com.bnppf.kata;

import com.bnppf.kata.service.BasketService;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceTest {

  private BasketService basketService;

  @BeforeEach
  public void setUp() {
    basketService = new BasketService();
  }


  @Test
  public void testCalculatePrice_NoBooks() {
    System.out.println(" No books Test case");
    //No Books
    double totalPrice = basketService.calculatePrice(new int[]{});
    Assertions.assertEquals(0.0, totalPrice);
  }

  @Test
  public void testCalculatePrice_OneBook() {
    System.out.println(" One book Test case");
    final int[] booksCount = basketService.addBooks(Collections.singletonList("Clean Code"));
    double totalPrice = basketService.calculatePrice(booksCount);
    Assertions.assertEquals(50.0, totalPrice);
  }

  @Test
  public void testCalculatePrice_TwoDifferentBooks() {
    System.out.println(" Two Unique book Test case");
    final int[] booksCount = basketService.addBooks(Arrays.asList("Clean Code", "Clean Coder"));
    double totalPrice = basketService.calculatePrice(booksCount);
    Assertions.assertEquals(95.0, totalPrice);
  }

  @Test
  public void testCalculatePrice_ThreeDifferentBooks() {
    System.out.println(" Three Unique book Test case");
    final int[] booksCount = basketService.addBooks(Arrays.asList("Clean Code", "Clean Coder", "Clean Architecture"));
    double totalPrice = basketService.calculatePrice(booksCount);
    Assertions.assertEquals(135.0, totalPrice);
  }

  /*
  Price of four different books = 4 * 50 = 200
Discount = 20% of 200 = 40
Expected price = 200 - 40 = 160

   */
  @Test
  public void testCalculatePrice_FourDifferentBooks() {
    System.out.println(" four Unique book Test case");
    final int[] booksCount = basketService.addBooks(Arrays.asList("Clean Code", "Clean Coder", "Clean Architecture", "Test Driven Development by Example"));
    double totalPrice = basketService.calculatePrice(booksCount);
    double expectedPrice = 160;
    Assertions.assertEquals(expectedPrice, totalPrice);
  }

  /*
  Price of all 5 books = 5 * 50 = 250
Discount = 25% of 250 = 62.5
Expected price = 250 - 62.5 = 187.5

   */
  @Test
  public void testCalculatePrice_FiveDifferentBooks() {
    System.out.println(" Five Unique book Test case");
    final int[] booksCount = basketService.addBooks(Arrays.asList("Clean Code", "Clean Coder", "Clean Architecture",
        "Test Driven Development by Example", "Working Effectively With Legacy Code"));

    double totalPrice = basketService.calculatePrice(booksCount);
    double expectedPrice = 187.5; // 25% discount on all books
    Assertions.assertEquals(expectedPrice, totalPrice);
  }

  @Test
  public void testCalculatePrice_MultipleCopiesOfEachBook() {
    System.out.println(" Multi Books Test case");
    final int[] booksCount = basketService.addBooks(Arrays.asList("Clean Code", "Clean Code",
        "Clean Coder", "Clean Coder",
        "Clean Architecture", "Clean Architecture",
        "Test Driven Development by Example",
        "Working Effectively With Legacy Code"));
    double totalPrice = basketService.calculatePrice(booksCount);
    double expectedPrice = 320.0; // (4 * 50) - 20% + (4 * 50) - 20%
    Assertions.assertEquals(expectedPrice, totalPrice);

  }

}