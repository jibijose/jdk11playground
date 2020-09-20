package com.example.demo.consumersupplier;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;

public class CustomConsumerSupplier {

  @Test
  public void ConsumerSupplierInfinite(){
    Supplier<String> mySupplier = new StringSupplierInfinite();
    Consumer<String> myConsumer = new StringConsumer();

    Stream<String> myStream = Stream.generate(mySupplier);
    myStream.forEach(myConsumer);
  }

  @Test
  public void ConsumerSupplierFinite(){
    Spliterator<String> mySpliterator = new StringSpliterator();
    Consumer<String> myConsumer = new StringConsumer();

    StreamSupport.stream(mySpliterator, false).parallel().forEach(myConsumer);
  }
}
