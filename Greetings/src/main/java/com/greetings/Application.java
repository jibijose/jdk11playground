package com.greetings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.astro.price.PriceService;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class).web(false).run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Greetings!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + PriceService.getWorldName());
  }
}
