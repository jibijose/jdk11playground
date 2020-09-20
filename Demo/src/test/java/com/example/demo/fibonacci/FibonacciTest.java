package com.example.demo.fibonacci;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
public class FibonacciTest {

  private static Instant startTime;

  @BeforeAll
  public static void beforeAll() {
    startTime = Instant.now();
  }

  @Test
  public void checkNumberNegative() {
    Assertions.assertThrows(
        RuntimeException.class,
        () -> {
          Fibonacci.getFibonacci(-1);
        });
  }

  @Test
  public void checkNumberZero() {
    Assertions.assertEquals(BigInteger.ZERO, Fibonacci.getFibonacci(0));
  }

  @Test
  public void checkNumberOne() {
    Assertions.assertEquals(BigInteger.ONE, Fibonacci.getFibonacci(1));
  }

  @Test
  public void checkNumberTwo() {
    Assertions.assertEquals(BigInteger.TWO, Fibonacci.getFibonacci(2));
  }

  @Test
  public void checkNumberThree() {
    Assertions.assertEquals(BigInteger.valueOf(6), Fibonacci.getFibonacci(3));
  }

  @Test
  public void checkNumberFive() {
    Assertions.assertEquals(BigInteger.valueOf(120), Fibonacci.getFibonacci(5));
  }

  @Test
  public void checkNumberTen() {
    Assertions.assertEquals(BigInteger.valueOf(3628800), Fibonacci.getFibonacci(10));
  }

  @Test
  public void checkNumberTwentyFive() {
    Assertions.assertEquals(
        new BigInteger("15511210043330985984000000"), Fibonacci.getFibonacci(25));
  }

  @Test
  public void checkNumberFifty() {
    Assertions.assertEquals(
        new BigInteger("30414093201713378043612608166064768844377641568960512000000000000"),
        Fibonacci.getFibonacci(50));
  }

  @Test
  public void checkNumberHundred() {
    Assertions.assertEquals(
        new BigInteger(
            "93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000"),
        Fibonacci.getFibonacci(100));
  }

  @Test
  public void printFibonaccis() {
    Instant allStartTime = Instant.now();
    IntStream.rangeClosed(1, 10000)
        .forEach(
            number -> {
              log.info("Fibonacci of {} is {}", number, Fibonacci.getFibonacci(number));
            });
    Instant allStopTime = Instant.now();
    log.info("Print all time is {}", Duration.between(allStartTime, allStopTime).toSeconds());
  }

  @AfterAll
  public static void afterAll() {
    Instant stopTime = Instant.now();
    log.info("Run time is {}", Duration.between(startTime, stopTime).toMillis());
  }
}
