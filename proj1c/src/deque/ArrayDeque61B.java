package deque;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArrayDeque61B<T> implements Deque61B<T> {
    private int size;
    int arrSize = 8;
    private int nextFirst;
    private int nextLast;
    private T[] arr;

    public ArrayDeque61B() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        arr = (T[]) new Object[arrSize];
    }

    public void resize(int n) {
        T[] newArr = (T[]) new Object[2 * n];
        int j = 0;
        for (int i = nextLast; i < arrSize; i++) {
            newArr[j] = this.get(i);
            j++;
        }
        for (int i = 0; i < nextLast; i++) {
            newArr[j] = this.get(i);
            j++;
        }
        arr = newArr;
        arrSize = 2 * n;
        nextFirst = arrSize - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if (size < arrSize) {
            arr[nextFirst] = x;
            if (nextFirst != 0) {
                nextFirst -= 1;
            } else {
                nextFirst = arrSize - 1;
            }
            size += 1;
        } else {
            resize(size);
            addFirst(x);
        }
    }

    @Override
    public void addLast(T x) {
        if (size < arrSize) {
            arr[nextLast] = x;
            if (nextLast != arrSize - 1) {
                nextLast += 1;
            } else {
                nextLast = 0;
            }
            size += 1;
        } else {
            resize(size);
            addLast(x);
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < arrSize; i++) {
            returnList.add(this.get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T some;
        if (isEmpty()) {
            return null;
        }
        if (nextFirst == arrSize - 1) {
            some = arr[0];
            arr[0] = null;
            nextFirst = 0;
        } else {
            some = arr[nextFirst + 1];
            arr[nextFirst + 1] = null;
            nextFirst += 1;
        }
        size -= 1;
        return some;
    }

    @Override
    public T removeLast() {
        T some;
        if (isEmpty()) {
            return null;
        }
        if (nextLast == 0) {
            some = arr[arrSize - 1];
            arr[arrSize - 1] = null;
            nextLast = arrSize - 1;
        } else {
            some = arr[nextLast - 1];
            arr[nextLast - 1] = null;
            nextLast -= 1;
        }
        size -= 1;
        return some;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > arrSize - 1) {
            return null;
        } else {
            return arr[index];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int count;

        public ArrayDequeIterator() {
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < arrSize;
        }

        @Override
        public T next() {
            T returnSomething = arr[count];
            count += 1;
            return returnSomething;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof ArrayDeque61B<?> o) {
            if (o.size != this.size) {
                return false;
            }

            for (int i = 0; i < arrSize; i++) {
                if (this.get(i) != o.get(i)) {
                    return false;
                }
            }
            return true;

        }
        return false;
    }

    @Override
    public String toString(){
        return toList().toString();
    }


}
