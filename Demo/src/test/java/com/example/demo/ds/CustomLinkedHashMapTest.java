package com.example.demo.ds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomLinkedHashMapTest {

  @Test
  public void testIsEmpty() {
    CustomLinkedHashMap customLinkedHashMap = new CustomLinkedHashMap();

    Assertions.assertEquals(0, customLinkedHashMap.size());
    Assertions.assertEquals(Boolean.TRUE, customLinkedHashMap.isEmpty());
  }

  @Test
  public void testGetAndPut() {
    CustomLinkedHashMap customLinkedHashMap = new CustomLinkedHashMap();
    customLinkedHashMap.put("jibi1", "jose1");
    Assertions.assertEquals("jose1", customLinkedHashMap.get("jibi1"));
    Assertions.assertEquals(1, customLinkedHashMap.size());
    Assertions.assertEquals(Boolean.FALSE, customLinkedHashMap.isEmpty());
  }

  @Test
  public void testGetAndOverwritePut() {
    CustomLinkedHashMap customLinkedHashMap = new CustomLinkedHashMap();
    customLinkedHashMap.put("jibi1", "jose1");
    customLinkedHashMap.put("jibi1", "jose2");
    customLinkedHashMap.put("jibi1", "jose3");
    Assertions.assertEquals("jose3", customLinkedHashMap.get("jibi1"));
    Assertions.assertEquals(1, customLinkedHashMap.size());
    Assertions.assertEquals(Boolean.FALSE, customLinkedHashMap.isEmpty());
  }

  @Test
  public void testGetAndMultiplePut() {
    CustomLinkedHashMap customLinkedHashMap = new CustomLinkedHashMap();
    customLinkedHashMap.put("jibi1", "jose1");
    customLinkedHashMap.put("jibi2", "jose2");
    //customLinkedHashMap.put("jibi1", "jose3");
    Assertions.assertEquals("jose2", customLinkedHashMap.get("jibi2"));
    //Assertions.assertEquals("jose3", customLinkedHashMap.get("jibi1"));
    Assertions.assertEquals(2, customLinkedHashMap.size());
    Assertions.assertEquals(Boolean.FALSE, customLinkedHashMap.isEmpty());
  }
}
