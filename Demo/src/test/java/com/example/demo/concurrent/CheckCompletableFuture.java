package com.example.demo.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckCompletableFuture {

  @Test
  public void testCompletableFuture() throws Exception {
    int LOOP_COUNT = 2;
    CompletableFuture[] completableFutures = new CompletableFuture[LOOP_COUNT];
    for (int i = 0; i < LOOP_COUNT; i++) {
      completableFutures[i] =
          CompletableFuture.supplyAsync(() -> Order.getOrder())
              .thenApply(order -> order.enrich(order))
              .thenApply(order -> order.performPayment(order))
              .thenApply(order -> order.dispatch(order))
              .thenApply(order -> order.sendEmail(order));
    }
    System.out.println("*****************************************************************");
    log.debug("Main thread waiting...");
    TimeUnit.SECONDS.sleep(10);
    log.debug("Main thread waited 10 seconds...");
    System.out.println("*****************************************************************");

    for (int i = 0; i < LOOP_COUNT; i++) {
      completableFutures[i].get();
    }
  }
}
