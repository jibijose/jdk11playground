package com.example.demo.consumersupplier;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class CustomConsumerSupplier {

  @Test
  public void ConsumerSupplierWithDelay(){
    Consumer<String> myConsumer = new StringConsumer();
    Supplier<String> mySupplier = new StringSupplier();

    Stream<String> myStream = Stream.generate(mySupplier);
    myStream.forEach(myConsumer);
  }
}
