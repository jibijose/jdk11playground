package com.example.demo.consumersupplier;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringSpliterator implements Spliterator<String> {

  private int index = 0;
  private String[] store = {"hi", "jibi", "jose", "hello", "world", "end"};

  @Override
  public boolean tryAdvance(Consumer<? super String> action) {
    if (index < store.length) {
      log.debug("Supplying [{}] with index {}", store[index], index);
      action.accept(store[index]);
      index++;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Spliterator<String> trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return store.length;
  }

  @Override
  public int characteristics() {
    return Arrays.stream(store).spliterator().characteristics();
  }
}
