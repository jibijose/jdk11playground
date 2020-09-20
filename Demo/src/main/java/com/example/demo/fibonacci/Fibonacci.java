package com.example.demo.fibonacci;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

  private static Map<BigInteger, BigInteger> CACHE = new HashMap<>();

  public static BigInteger getFibonacci(int number) {
    return getFibonacci(BigInteger.valueOf(number));
  }

  public static BigInteger getFibonacci(BigInteger number) {
    if (number.compareTo(BigInteger.ZERO) < 0) {
      throw new RuntimeException("Negative number");
    }

    if (CACHE.containsKey(number)) {
      return CACHE.get(number);
    }

    if (number.compareTo(BigInteger.ZERO) == 0) {
      CACHE.put(BigInteger.ZERO, number);
      return number;
    }
    if (number.compareTo(BigInteger.ONE) == 0) {
      CACHE.put(BigInteger.ONE, number);
      return number;
    }

    BigInteger resultNumber = number.multiply(getFibonacci(number.subtract(BigInteger.ONE)));
    CACHE.put(number, resultNumber);
    return resultNumber;
  }
}
