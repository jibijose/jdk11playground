package com.example.demo.consumersupplier;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;

public class CustomConsumerSupplierTest {

  @Test
  public void ConsumerSupplierInfinite() {
    Supplier<String> mySupplier = new StringSupplierInfinite();
    Consumer<String> myConsumer = new StringConsumer();

    Stream<String> myStream = Stream.generate(mySupplier).limit(10);
    myStream.forEach(myConsumer);
  }

  @Test
  public void ConsumerSupplierFinite() {
    String[] store = {"hi", "jibi", "jose", "hello", "world", "end"};
    Spliterator<String> mySpliterator = new StringSpliterator(store);
    Consumer<String> myConsumer = new StringConsumer();

    StreamSupport.stream(mySpliterator, false)
        .filter(s -> s.length() <= 5 )
        .parallel().forEach(myConsumer);
  }

  @Test
  public void ConsumerSupplierFiniteWithPredicate() {
    String[] store = {"hi", "jibi", "jose", "hello", "world", "end"};
    Spliterator<String> mySpliterator = new StringSpliterator(store);
    Consumer<String> myConsumer = new StringConsumer();

    StreamSupport.stream(mySpliterator, false)
        .filter(new StringPredicate(3))
        .parallel()
        .forEach(myConsumer);
  }

  @Test
  public void CustomForkJoinPool() {}
}
