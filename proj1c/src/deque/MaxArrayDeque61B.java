package deque;


import java.util.Comparator;



public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> defaultComparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        defaultComparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        int i = 0;
        T maxItem;
        while (get(i) == null) {
            i++;
        }
        maxItem = get(i);
        for (int j = 0; j < arrSize; j++) {
            T currItem = get(j);
            if (currItem == null) {
                continue;
            }
            if (defaultComparator.compare(currItem, maxItem) > 0) {
                maxItem = currItem;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int i = 0;
        T maxItem;
        while (get(i) == null) {
            i++;
        }
        maxItem = get(i);
        for (int j = 0; j < size(); j++) {
            T currItem = get(j);
            if (currItem == null) {
                continue;
            }
            if (c.compare(currItem, maxItem) > 0) {
                maxItem = currItem;
            }
        }
        return maxItem;
    }
}

