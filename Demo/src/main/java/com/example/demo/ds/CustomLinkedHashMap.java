package com.example.demo.ds;

import com.example.demo.ds.CustomHashMap.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomLinkedHashMap<K, V> implements Map<K, V> {

  private int capacity = 1;
  private Entry<K, V>[] table = new Entry[capacity];
  private Entry<K, V> first = null;
  private Entry<K, V> last = null;

  @Override
  public V get(Object key) {
    int bucket = key.hashCode() % capacity;
    Entry<K, V> checkEntry = table[bucket];
    while (checkEntry != null) {
      if (checkEntry.key.equals(key)) {
        return checkEntry.value;
      }
      checkEntry = checkEntry.next;
    }
    return null;
  }

  @Override
  public V put(K key, V value) {
    int bucket = key.hashCode() % capacity;

    Entry<K, V> lastEntry = table[bucket];
    if (lastEntry == null) {
      if (last == null) {
        Entry<K, V> entry = new Entry(key, value, null, null, null);
        table[bucket] = entry;
        last = entry;
        return value;
      }
      Entry<K, V> entry = new Entry(key, value, last, null, null);
      table[bucket] = entry;
      last = entry;
      return value;
    }

    if (lastEntry.key.equals(key)) {
      lastEntry.value = value;
      return value;
    }

    while (lastEntry.next != null) {
      lastEntry = lastEntry.next;
      if (lastEntry.key.equals(key)) {
        lastEntry.value = value;
        return value;
      }
    }
    Entry<K, V> entry = new Entry(key, value, last, null, null);
    lastEntry.next = entry;
    last = entry;
    return value;
  }

  @Override
  public int size() {
    int size = 0;
    for (int i = 0; i < table.length; i++) {
      Entry<K, V> entry = table[i];
      while (entry != null) {
        size++;
        entry = entry.next;
      }
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    boolean isEmpty = true;
    for (int i = 0; i < table.length; i++) {
      Entry<K, V> entry = table[i];
      if (entry != null) {
        isEmpty = false;
        break;
      }
    }
    return isEmpty;
  }

  @Override
  public boolean containsKey(Object key) {
    if (get(key) != null) {
      return true;
    }
    return false;
  }

  @Override
  public boolean containsValue(Object value) {
    for (int i = 0; i < table.length; i++) {
      Entry<K, V> entry = table[i];
      while (entry != null) {
        if (entry.value.equals(value)) {
          return true;
        }
        entry = entry.next;
      }
    }
    return false;
  }

  @Override
  public V remove(Object key) {
    return null;
  }

  @Override
  public void putAll(Map m) {}

  @Override
  public void clear() {
    for (int i = 0; i < table.length; i++) {
      table[i] = null;
    }
  }

  @Override
  public Set keySet() {
    Set<K> keySet = new HashSet<>();
    for (int i = 0; i < table.length; i++) {
      Entry<K, V> entry = table[i];
      while (entry != null) {
        keySet.add(entry.key);
        entry = entry.next;
      }
    }
    return keySet;
  }

  @Override
  public Collection values() {
    Collection<V> values = new ArrayList<>();
    for (int i = 0; i < table.length; i++) {
      Entry<K, V> entry = table[i];
      while (entry != null) {
        values.add(entry.value);
        entry = entry.next;
      }
    }
    return values;
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> entrySet = new HashSet<>();
    for (int i = 0; i < table.length; i++) {
      Entry<K, V> entry = table[i];
      if (entry != null) {
        entrySet.add(entry);
      }
    }
    return entrySet;
  }

  static class Entry<K, V> implements Map.Entry<K, V> {
    private Entry<K, V> next;
    private K key;
    private V value;
    private Entry<K, V> previous;
    private Entry<K, V> after;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public Entry(K key, V value, Entry previous, Entry after) {
      this.key = key;
      this.value = value;
      this.previous = previous;
      this.after = after;
    }

    public Entry(K key, V value, Entry previous, Entry after, Entry next) {
      this.key = key;
      this.value = value;
      this.previous = previous;
      this.after = after;
      this.next = next;
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V value) {
      return this.value = value;
    }

    public boolean equals(Object object) {
      return false;
    }

    public int hashCode() {
      return key.hashCode();
    }
  }
}
