package com.example.demo.consumersupplier;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringSpliterator implements Spliterator<String> {

  private int index = 0;

  private String[] store = null;
  private int start = 0;
  private int end = 0;

  public StringSpliterator(String[] store) {
    this(store, 0, store.length - 1);
  }

  public StringSpliterator(String[] store, int start, int end) {
    this.store = store;
    this.start = start;
    this.end = end;

    index = start;
  }

  @Override
  public boolean tryAdvance(Consumer<? super String> action) {
    if (index >= start && index <= end) {
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
    if ((end - start) <= 0) {
      return null;
    }

    int mid = start + (end - start + 1) / 2 - 1;
    log.debug("[{}, {}] -> [{}, {}] + [{}, {}]", start, end, start, mid, mid+1, end);
    int secondEnd = end;
    end = mid;
    return new StringSpliterator(store, end + 1, secondEnd);
  }

  @Override
  public long estimateSize() {
    return (end - start + 1);
  }

  @Override
  public int characteristics() {
    return Arrays.stream(store).spliterator().characteristics();
  }
}
