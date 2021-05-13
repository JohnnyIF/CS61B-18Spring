public class LinkedListDeque<T> {
    public class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T item, Node prev, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node pointer = this.sentinel;
        while (pointer.next != sentinel) {
            pointer = pointer.next;
            System.out.println(pointer.item);
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;

        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        Node current = sentinel.next;
        while (index > 0) {
            current = current.next;
            index -= 1;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getHelper(sentinel.next, index);

    }

    private T getHelper(Node current, int index) {
        if (index == 0) {
            return current.item;
        }
        return getHelper(current.next, index - 1);
    }


}
