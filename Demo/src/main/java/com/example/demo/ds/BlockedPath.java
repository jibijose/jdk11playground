package com.example.demo.ds;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockedPath {

  boolean[][] field;
  int fieldSize;
  int endX;
  int endY;

  public BlockedPath(int fieldSize, int endX, int endY) {
    this.fieldSize = fieldSize;
    field = new boolean[fieldSize][fieldSize];
    this.endX = endX;
    this.endY = endY;
    initialize();
  }

  private void initialize() {
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        field[j][i] = false;
      }
    }
    field[0][1] = true;
    field[1][1] = true;
  }

  public void checkPath(int x, int y) {
    log.debug("Checking [{}][{}]", x, y);
    field[y][x] = true;
    if (x == endX && y == endY) {
      System.out.println("Ending!!!");
      return;
    }

    if (withinBoundary(x + 1, y) && !field[y][x + 1]) {
      checkPath(x + 1, y);
    }
    if (withinBoundary(x - 1, y) && !field[y][x - 1]) {
      checkPath(x - 1, y);
    }
    if (withinBoundary(x, y + 1) && !field[y + 1][x]) {
      checkPath(x, y + 1);
    }
    if (withinBoundary(x, y - 1) && !field[y - 1][x]) {
      checkPath(x, y - 1);
    }

    log.debug("Checked [{}][{}]", x, y);
    field[y][x] = false;
  }

  private boolean withinBoundary(int x, int y) {
    if (x >= 0 && x < fieldSize && y >= 0 && y < fieldSize) {
      return true;
    }
    return false;
  }
}
