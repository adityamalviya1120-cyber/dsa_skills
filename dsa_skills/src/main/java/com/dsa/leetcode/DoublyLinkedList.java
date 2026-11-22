package com.dsa.leetcode;

class DoublyLinkedList {

    static class Node {
        public int data;
        public Node next;
        public Node prev;

        Node(int data) {
            this.data = data;
        }
    }

    Node head;
    Node tail;


    //insert at end
    public void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void delete(int data) {
        Node current = head;

        while (current != null) {
            if (current.data == data) {

                if (current == head) {
                    head = current.next;
                }

                if (current == tail) {
                    tail = current.prev;
                }

                if (current.prev != null) {
                    current.prev.next = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                }

                return;
            }
        }
    }

    public void displayForward() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("NULL");
    }

    public void displayBackward() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("NULL");
    }

    static void main() {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insert(10);
        dll.insert(20);
        dll.insert(30);

        System.out.println("Forward");
        dll.displayForward();
        System.out.println("Backward");
        dll.displayBackward();

        dll.insertAtBeginning(5);

        System.out.println("After Insert 5 : ");
        dll.displayForward();

        dll.delete(20);

        System.out.println("After Delete 20 : ");
        dll.displayForward();
    }
}

