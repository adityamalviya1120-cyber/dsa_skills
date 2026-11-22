package com.dsa.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // 1. One box in our list
    private static class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0); // fake start
    private final Node tail = new Node(0, 0); // fake end

    // 2. Setup
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    // 3. get
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;   // not found
        remove(node);                  // take it out
        addToFront(node);              // put it at the front
        return node.value;
    }

    // 4. put
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {            // key already exists
            node.value = value;
            remove(node);
            addToFront(node);
            return;
        }
        if (map.size() == capacity) {  // desk is full
            Node lru = tail.prev;      // the oldest box
            remove(lru);
            map.remove(lru.key);
        }
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        addToFront(newNode);
    }

    // 5. Helper: unlink a box from the list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 6. Helper: place a box right after head
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    static void main() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);            // list: 1
        cache.put(2, 20);            // list: 2, 1
        cache.get(1);                // returns 10, list: 1, 2
        cache.put(3, 30);            // full, removes 2. List: 3, 1
        System.out.println(cache.get(2)); // -1 (it was thrown out)
    }
}
