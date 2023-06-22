package com.basicstrong.functionalprogramming.section14;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapFun<K, V> {

  private final Entry[] entries;
  private final int size;

  public MapFun(int size) {
    this.size = size;
    this.entries = new Entry[size];

    for (int i = 0; i < size; i++) {
      this.entries[i] = new Entry();
    }

  }

  private MapFun(Entry[] entries, int size) {
    this.size = size;
    this.entries = entries;
  }

  public Integer getHash(K key) {
    return key.hashCode() % size;
  }


  public MapFun<K, V> put(K key, V value) {
    int hash = getHash(key);
    Entry existingEntry = entries[hash];
    if (isDuplicate(key)) {
      existingEntry.value = value;
    }
    Entry newEntry = new Entry(key, value);
    entries[hash] = newEntry;
    newEntry.next = existingEntry;

    return new MapFun<>(entries, size);
  }

  private boolean isDuplicate(K key) {
    Entry entry = entries[getHash(key)];
    while (entry != null) {
      if (key.equals(entry.key)) {
        return true;
      }
      entry = entry.next;
    }
    return false;
  }

  @SuppressWarnings(value = "unchecked")
  public V getValue(K key) {
    V val = null;
    int hash = getHash(key);
    Entry entry = entries[hash];

    while (entry.next != null) {
      if (key.equals(entry.getKey())) {
        val = (V) entry.getValue();
        break;
      }
      entry = entry.next;
    }

    return val;
  }

  public void display() {
    for (Entry entry : this.entries) {
      log.info(entry.toString());
    }
  }
}