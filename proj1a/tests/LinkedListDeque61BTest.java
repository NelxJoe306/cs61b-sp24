import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    public void testadd() {
        LinkedListDeque61B<Integer> l = new LinkedListDeque61B<>();
        l.addFirst(1);
        l.addFirst(2);
        assertThat(l.get(1)).isEqualTo(2);
    }

    @Test
    public void testSizeAndIsEmpty() {
        LinkedListDeque61B<Integer> l1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> l2 = new LinkedListDeque61B<>();
        l1.addFirst(1);
        l1.addLast(2);
        assertThat(l1.size).isEqualTo(2);
        assertThat(l2.size).isEqualTo(0);
        assertThat(l1.isEmpty()).isFalse();
        assertThat(l2.isEmpty()).isTrue();
    }

    @Test
    public void testGet() {
        LinkedListDeque61B<Integer> l1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<String> l2 = new LinkedListDeque61B<>();
        LinkedListDeque61B<String> l3 = new LinkedListDeque61B<>();

        l1.addFirst(1);
        l1.addFirst(2);
        l1.addLast(5);

        l2.addLast("sunyu");
        l2.addFirst("miku");

        int actual1 = l1.get(3);
        String actual2 = l2.get(1);

        assertThat(actual1).isEqualTo(5);
        assertThat(actual2).isEqualTo("miku");
        assertThat(l3.get(114514)).isEqualTo(null);
        assertThat(l2.get(1919810)).isEqualTo(null);
    }

    @Test
    public void testGetRecursive() {
        LinkedListDeque61B<Integer> l1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<String> l2 = new LinkedListDeque61B<>();
        LinkedListDeque61B<String> l3 = new LinkedListDeque61B<>();

        l1.addFirst(1);
        l1.addFirst(2);
        l1.addLast(5);

        l2.addLast("sunyu");
        l2.addFirst("miku");

        int actual1 = l1.getRecursive(3);
        String actual2 = l2.getRecursive(1);

        assertThat(actual1).isEqualTo(5);
        assertThat(actual2).isEqualTo("miku");
        assertThat(l3.getRecursive(114514)).isEqualTo(null);
        assertThat(l2.getRecursive(1919810)).isEqualTo(null);
    }

    @Test
    public void testReMoveFirst() {
        LinkedListDeque61B<Integer> l = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> emptyL = new LinkedListDeque61B<>();
        l.addFirst(1);
        l.addFirst(2);
        l.addLast(5);
        l.addFirst(7);
        int remover1 = l.removeFirst();
        assertThat(l.toList()).containsExactly(2, 1, 5).inOrder();
        assertThat(remover1).isEqualTo(7);
        assertThat(emptyL.removeFirst()).isEqualTo(null);
    }
    @Test
    public void testReMoveLast() {
        LinkedListDeque61B<Integer> l = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> emptyL = new LinkedListDeque61B<>();
        l.addFirst(1);
        l.addFirst(2);
        l.addLast(5);
        l.addFirst(7);
        int remover1 = l.removeLast();
        assertThat(l.toList()).containsExactly(7, 2, 1).inOrder();
        assertThat(remover1).isEqualTo(5);
        assertThat(emptyL.removeFirst()).isEqualTo(null);
    }
}