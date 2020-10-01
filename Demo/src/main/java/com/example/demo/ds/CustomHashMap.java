package com.example.demo.ds;

import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CustomHashMap<K, V> implements Map<K, V> {

  private int capacity = 1;
  private Entry<K, V>[] table = new Entry[capacity];

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

    Entry<K, V> entry = new Entry(key, value);
    Entry<K, V> lastEntry = table[bucket];
    if (lastEntry == null) {
      table[bucket] = entry;
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
    lastEntry.next = entry;
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
    int bucket = key.hashCode() % capacity;
    Entry<K, V> checkEntry = table[bucket];
    if (checkEntry == null) {
      return null;
    }

    if (checkEntry.key.equals(key)) {
      table[bucket] = checkEntry.next;
      return checkEntry.value;
    }

    while (checkEntry.next != null) {
      if (checkEntry.next.key.equals(key)) {
        V value = checkEntry.next.value;
        checkEntry.next = checkEntry.next.next;
        return value;
      }
    }
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

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public V setValue(V value) {
      this.value = value;
      return value;
    }

    public boolean equals(Object object) {
      return false;
    }

    public int hashCode() {
      return key.hashCode();
    }
  }
}
