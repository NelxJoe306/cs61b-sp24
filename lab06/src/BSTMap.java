import java.util.Iterator;
import java.util.Set;


public class BSTMap<k extends Comparable<k>, v> implements Map61B<k, v> {
    public class Node {
        k key;
        v value;
        Node left;
        Node right;
        Node parent;


        Node(k K, v V) {
            key = K;
            value = V;
        }
    }

    private int size = 0;
    private Node root;

    @Override
    public void put(k key, v value) {
        root = putHelper(root, key, value);
    }

    private Node putHelper(Node n, k key, v value) {
        if (n == null) {
            size += 1;
            return new Node(key, value);
        }
        int cmp = key.compareTo(n.key);

        if (cmp < 0) {
            n.left = putHelper(n.left, key, value);
        } else if (cmp > 0) {
            n.right = putHelper(n.right, key, value);
        } else {
            n.value = value;
        }

        return n;
    }

    @Override
    public v get(k key) {
        Node n = findCurrOfkey(key, root);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    private Node findCurrOfkey(k key, Node theNode) {
        if (theNode == null) {
            return theNode;
        }
        if (!theNode.key.equals(key)) {
            if (key.compareTo(theNode.key) > 0) {
                return findCurrOfkey(key, theNode.right);
            } else {
                return findCurrOfkey(key, theNode.left);
            }
        } else {
            return theNode;

        }
    }

    @Override
    public boolean containsKey(k key) {
        if (findCurrOfkey(key, root) != null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<k> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public v remove(k key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<k> iterator() {
        throw new UnsupportedOperationException();
    }
}
