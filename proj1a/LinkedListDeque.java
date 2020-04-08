public class LinkedListDeque<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T i, Node p, Node n) {
            item  = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        // sentinel 之后是第一个元素
        // 所以新节点肯定是在 sentinel 和 sentinel.next 之间
        Node newNode = new Node(item, sentinel, sentinel.next);
        // 要把新节点放到 sentinel 和 sentinel.next 之间
        // 就要改掉 sentinel.next 的前驱 和 sentinel 的后继
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        // sentinel 之前是最后一个元素
        // 所以新节点肯定是在 sentinel.prev 和 sentinel 之间
        Node newNode = new Node(item, sentinel.prev, sentinel);
        // 要把新节点放到 sentinel.prev 和 sentinel 之间
        // 就要改掉 sentinel.prev 的后继 和 sentinel 的前驱
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = 0;
        Node p = sentinel.next;
        while (index < size - 1) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
            index++;
        }
        System.out.println(p.item);
    }

    public T removeFirst() {
        Node theNode = sentinel.next;
        T ret = theNode.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        theNode = null;
        size--;
        return ret;
    }

    public T removeLast() {
        Node theNode = sentinel.prev;
        T ret = theNode.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        theNode = null;
        size--;
        return ret;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        int count = 0;
        while (count != index) {
            p = p.next;
            count++;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, 0, index);
    }

    private T getRecursive(Node p, int counter, int target) {
        if (counter == target) {
            return p.item;
        }
        return getRecursive(p.next, counter + 1, target);
    }
}
