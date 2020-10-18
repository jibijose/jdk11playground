package com.example.demo.concurrent;

import static com.example.demo.common.Util.sleepSilentRandom;

import com.example.demo.common.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyRunnable implements Runnable {

  @Override
  public void run() {
    int sleepSeconds = Util.getRandom();
    log.debug("Running with random number {}", sleepSeconds);
    sleepSilentRandom();
    log.debug("Ran with random number {}", sleepSeconds);
  }
}
