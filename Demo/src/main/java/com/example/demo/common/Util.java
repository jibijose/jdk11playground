package com.example.demo.common;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {

  private static Random random = new Random();

  public static void sleepSilent(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException interruptedException) {
      log.error("Thread sleep interrupted", interruptedException);
    }
  }

  public static void sleepSilentRandom(int upperBound) {
    sleepSilent(random.nextInt(upperBound));
  }

  public static void sleepSilentRandom() {
    sleepSilentRandom(10);
  }

  public static int getRandom(int upperBound) {
    return random.nextInt(upperBound);
  }

  public static int getRandom() {
    return getRandom(10);
  }
}
