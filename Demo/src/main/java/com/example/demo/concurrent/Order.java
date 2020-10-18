package com.example.demo.concurrent;

import static com.example.demo.common.Util.sleepSilentRandom;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Order {

  private static Random random = new Random();
  private static int ID_INDEX = 1;

  private int id;

  public Order() {
    sleepSilentRandom();
    this.id = ID_INDEX++;
  }

  public static Order getOrder() {
    log.debug("Getting order");
    Order order = new Order();
    log.debug("Got order {}", order.id);
    return order;
  }

  public Order enrich(Order order) {
    log.debug("Enriching order {}", order.id);
    sleepSilentRandom();
    log.debug("Enriched order {}", order.id);
    return order;
  }

  public Order performPayment(Order order) {
    log.debug("Performing payment order {}", order.id);
    sleepSilentRandom();
    log.debug("Performed payment order {}", order.id);
    return order;
  }

  public Order dispatch(Order order) {
    log.debug("Dispatching order {}", order.id);
    sleepSilentRandom();
    log.debug("Dispatched order {}", order.id);
    return order;
  }

  public Order sendEmail(Order order) {
    log.debug("Emailing order {}", order.id);
    sleepSilentRandom();
    log.debug("Emailing order {}", order.id);
    return order;
  }
}
