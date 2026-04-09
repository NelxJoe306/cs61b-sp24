import java.util.ArrayList; // import the ArrayList class
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    int size = 0;
    public Node sential;

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
        sential = new Node(null, null, null);
        sential.prev = sential;
        sential.next = sential;
    }

    @Override
    public void addFirst(T x) {
        Node newnode = new Node(sential, x, sential.next);
        sential.next.prev = newnode;
        sential.next = newnode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newnode = new Node(sential.prev, x, sential);
        sential.prev.next = newnode;
        sential.prev = newnode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnlist = new ArrayList<>();
        Node currnode = sential.next;
        while (currnode.item != null) {
            returnlist.add(currnode.item);
            currnode = currnode.next;
        }
        return returnlist;
    }

    @Override
    public boolean isEmpty() {
        return sential.next.item == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        T x = sential.next.item;
        sential.next.next.prev = sential;
        sential.next = sential.next.next;
        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        T x = sential.prev.item;
        sential.prev.prev.next = sential;
        sential.prev = sential.prev.prev;
        return x;
    }

    @Override
    public T get(int index) {
        T x;
        if (index > size) {
            return null;
        } else {
            Node currnode = sential;
            while (index > 0) {
                currnode = currnode.next;
                index -= 1;
            }
            x = currnode.item;
        }
        return x;
    }

    @Override
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
            T x = recursiveGet(sential,index);
            return x;
    }
    public T recursiveGet(Node currnode,int index){
        if (index==0){
            return currnode.item;
        }
        return recursiveGet(currnode.next, index-1);
    }
}
