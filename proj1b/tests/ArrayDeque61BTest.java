import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void testAddFirst() {
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(1);
        L.addFirst(5);
        assertThat(L.get(0)).isEqualTo(1);
        assertThat(L.get(7)).isEqualTo(5);
    }

    @Test
    public void testAddLast() {
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(3);
        L.addLast(4);
        assertThat(L.get(1)).isEqualTo(1);
        assertThat(L.get(2)).isEqualTo(5);
        assertThat(L.get(0)).isEqualTo(4);
        assertThat(L.get(7)).isEqualTo(3);
        assertThat(L.get(10)).isEqualTo(null);
    }


    @Test
    public void testIsEmptyAndSize(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        assertThat(L.size()).isEqualTo(0);
        assertThat(L.isEmpty()).isTrue();
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(3);
        L.addLast(4);
        assertThat(L.isEmpty()).isFalse();
        assertThat(L.size()).isEqualTo(8);
    }

    @Test
    public void testToList(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(3);
        L.addLast(4);
        assertThat(L.toList()).containsExactly(4,1,5,1,5,1,5,3).inOrder();
        Deque61B<Integer> L1 = new ArrayDeque61B<>();
        L1.addFirst(1);
        L1.addFirst(5);
        assertThat(L1.toList()).containsExactly(1,null,null,null,null,null,null,5);
    }

    @Test
    public void testRemoveFirst() {
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(3);
        L.addLast(4);
        L.removeFirst();
        L.removeFirst();
        assertThat(L.toList()).containsExactly(4,null,null,1,5,1,5,3);
        Deque61B<Integer> L1 = new ArrayDeque61B<>();
        L1.addFirst(1);
        L1.addFirst(5);
        int some = L1.removeFirst();
        assertThat(L1.get(7)).isEqualTo(null);
        assertThat(some).isEqualTo(5);
    }
    @Test
    public void testRemoveLast() {
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(3);
        L.addLast(4);
        L.removeLast();
        L.removeLast();
        assertThat(L.toList()).containsExactly(null,1,5,1,5,1,5,null).inOrder();
        Deque61B<Integer> L1 = new ArrayDeque61B<>();
        L1.addFirst(1);
        L1.addFirst(5);
        int some = L1.removeLast();
        assertThat(L1.get(0)).isEqualTo(null);
        assertThat(some).isEqualTo(1);
    }

    @Test
    public void testresize(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(1);
        L.addLast(5);
        L.addLast(3);
        L.addLast(4);
        assertThat(L.toList()).containsExactly(4,1,5,1,5,1,5,3).inOrder();
        L.addLast(9);
        assertThat(L.toList()).containsExactly(1, 5, 1, 5, 1, 5, 3, 4, 9,
                null, null, null, null, null, null, null);
        L.addFirst(8);
        assertThat(L.toList()).containsExactly(1, 5, 1, 5, 1, 5, 3, 4, 9,
                null, null, null, null, null, null, 8);
    }


}