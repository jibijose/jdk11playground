package com.example.demo.ds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomHashMapTest {

  @Test
  public void testIsEmpty() {
    CustomHashMap customHashMap = new CustomHashMap();

    Assertions.assertEquals(0, customHashMap.size());
    Assertions.assertEquals(Boolean.TRUE, customHashMap.isEmpty());
  }

  @Test
  public void testGetAndPut() {
    CustomHashMap customHashMap = new CustomHashMap();
    customHashMap.put("jibi1", "jose1");
    Assertions.assertEquals("jose1", customHashMap.get("jibi1"));
    Assertions.assertEquals(1, customHashMap.size());
    Assertions.assertEquals(Boolean.FALSE, customHashMap.isEmpty());
  }

  @Test
  public void testGetAndOverwritePut() {
    CustomHashMap customHashMap = new CustomHashMap();
    customHashMap.put("jibi1", "jose1");
    customHashMap.put("jibi1", "jose2");
    customHashMap.put("jibi1", "jose3");
    Assertions.assertEquals("jose3", customHashMap.get("jibi1"));
    Assertions.assertEquals(1, customHashMap.size());
    Assertions.assertEquals(Boolean.FALSE, customHashMap.isEmpty());
  }

  @Test
  public void testGetAndMultiplePut() {
    CustomHashMap customHashMap = new CustomHashMap();
    customHashMap.put("jibi1", "jose1");
    customHashMap.put("jibi2", "jose2");
    customHashMap.put("jibi1", "jose3");
    Assertions.assertEquals("jose2", customHashMap.get("jibi2"));
    Assertions.assertEquals("jose3", customHashMap.get("jibi1"));
    Assertions.assertEquals(2, customHashMap.size());
    Assertions.assertEquals(Boolean.FALSE, customHashMap.isEmpty());
  }
}
