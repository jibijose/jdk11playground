package com.example.demo.concurrent;

import static com.example.demo.common.Util.sleepSilentRandom;

import com.example.demo.common.Util;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyCallable<String> implements Callable {

  @Override
  public String call() throws Exception {
    int sleepSeconds = Util.getRandom();
    log.debug("Calling with random number {}", sleepSeconds);
    sleepSilentRandom();
    log.debug("Called with random number {}", sleepSeconds);
    return (String) ("" + sleepSeconds);
  }
}
