package org.itstep;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ArrayMap implements Map {

    private int count;
    private int initSize = 10;
    private Pair[] map;

    public static class Pair<K, V> implements Map.Entry {

        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
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
        public V setValue(Object value) {
            return (V) value;
        }
    }

    public ArrayMap() {
        clear();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Pair entry = getPair(key);
        return entry == null;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean contains = false;
        for (int i=0; i<count; i++) {
            if (map[i].getValue().equals(value)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public Object get(Object key) {
        Pair entry = getPair(key);
        Object value = null;
        if(entry != null) {
            value = entry.getValue();
        }
        return value;
    }

    @Override
    public Object put(Object key, Object value) {
        Pair old = getPair(key);
        if(old == null) {
            Pair item = new Pair(key, value);
            if (count >= map.length) {
                resizeMap();
            }
            map[count++] = item;
            return null;
        } else {
            Object oldValue = old.getValue();
            old.setValue(value);
            return oldValue;
        }
    }

    private Pair getPair(Object key) {
        Pair entry = null;
        for (int i=0; i<count; i++) {
            if (map[i].getKey().equals(key)) {
                entry = map[i];
                break;
            }
        }
        return entry;
    }

    private void resizeMap() {
        resizeMap(map.length + 10);
    }

    private void resizeMap(int newSize) {
        if(newSize > map.length) {
            Pair[] tmp = new Pair[newSize];
            System.arraycopy(map, 0, tmp, 0, map.length);
            map = tmp;
        }
    }

    @Override
    public Object remove(Object key) {
        int idx = -1;
        Object value = null;
        for(int i=0; i<map.length; i++) {
            if(map[i].getKey().equals(key)) {
                idx = i;
                value = map[i].getValue();
                break;
            }
        }
        if(idx != -1) {
            if (map.length - idx >= 0) System.arraycopy(map, idx + 1, map, idx, map.length - idx);
        }

        return value;
    }

    @Override
    public void clear() {
        map = new Pair[initSize];
        count = 0;
    }

    @Override
    public void putAll(Map m) {
        for(Object k: m.keySet()) {
            this.put(k, m.get(k));
        }
    }

    @Override
    public Set keySet() {
        return Arrays.stream(map).map(p -> p.key).collect(Collectors.toSet());
    }
}