package com.example.demo.consumersupplier;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringConsumer implements Consumer<String> {

  @Override
  public void accept(String s) {
    log.debug("Accepting string {}", s);
    try {
      Thread.sleep(100);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
    log.debug("Accepted string {}", s);
  }
}
