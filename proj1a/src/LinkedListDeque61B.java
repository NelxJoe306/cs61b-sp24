import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    int size = 0;
    private Node sential;

    public class Node {
        Node prev;
        Node next;
        T item;

        public Node(Node prev, T x, Node next) {
            item = x;
            this.prev = prev;
            this.next = next;
        }

    }

    public LinkedListDeque61B() {
        sential = new Node(sential, null, sential);
    }

    @Override
    public void addFirst(T x) {

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
