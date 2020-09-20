package com.example.demo.consumersupplier;

import java.util.function.Predicate;

public class StringPredicate implements Predicate<String> {

  private int maxSize = 0;

  public StringPredicate(int maxSize) {
    this.maxSize = maxSize;
  }

  @Override
  public boolean test(String s) {
    if (s.length() <= maxSize) {
      return true;
    } else {
      return false;
    }
  }
}
