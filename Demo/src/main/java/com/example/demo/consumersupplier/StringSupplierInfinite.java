package com.example.demo.consumersupplier;

import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringSupplierInfinite implements Supplier<String> {

  private int index = 0;

  @Override
  public String get() {
    log.debug("Getting string");
    String str = null;
    try {
      Thread.sleep(100);
      str = "name" + index;
      index++;
    } catch (InterruptedException ie) {
      ie.printStackTrace();
      str = "ERROR";
    }
    log.debug("Got string {}", str);
    return str;
  }
}
