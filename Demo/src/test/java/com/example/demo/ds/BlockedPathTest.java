package com.example.demo.ds;

import org.junit.jupiter.api.Test;

public class BlockedPathTest {

  @Test
  public void testBasicFieldSizeTwo() {
    BlockedPath blockedPath = new BlockedPath(3, 2, 0);
    blockedPath.checkPath(0, 0);
  }
}
