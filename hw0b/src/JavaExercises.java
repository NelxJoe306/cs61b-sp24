
import java.util.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;


@RunWith(Enclosed.class)
public class JavaExercises {
    public static int[] makeDice() {
        return new int[]{1, 2, 3, 4, 5, 6};
    }

    // Task1
    public static String[] takeOrder(String name) {
        if (Objects.equals(name, "Ergun")) {
            return new String[]{"beyti", "pizza", "hamburger", "tea"};

        } else if (Objects.equals(name, "Erik")) {
            return new String[]{"sushi", "pasta", "avocado", "coffee"};
        } else {
            return new String[3];
        }
    }

    public static int findMinMax(int[] arr) {
        int L = arr.length;
        int max = arr[0], min = arr[0];
        for (int i = 1; i < L; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return max - min;
    }

    public static List<Integer> hailstone(int n) {
        List<Integer> lst = new ArrayList<>();
        if (n <= 0) {
            throw new ArithmeticException("cannot less than zero");
        }
        class hailstoneHelper {
            public hailstoneHelper(int n, List<Integer> lst) {
                if (n == 1) {
                    lst.add(n);
                } else if (n % 2 == 0) {
                    lst.add(n);
                    n = n / 2;
                    new hailstoneHelper(n, lst);
                } else {
                    lst.add(n);
                    n = 3 * n + 1;
                    new hailstoneHelper(n, lst);
                }
            }
        }
        new hailstoneHelper(n, lst);
        return lst;
    }

    //Task2
    public static int sum(List<Integer> L) {
        if (L.isEmpty()) {
            return 0;
        } else {
            int sum = 0;
            for (int i = 0; i < L.size(); i++) {
                sum += L.get(i);
            }
            return sum;
        }
    }

    public static List<Integer> evens(List<Integer> L) {
        if (L.isEmpty()) {
            return L;
        } else {
            List<Integer> EL = new ArrayList<>();
            for (int i = 0; i < L.size(); i++) {
                if (L.get(i) % 2 == 0) {
                    EL.add(L.get(i));
                }
            }
            return EL;
        }
    }

    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> result = new ArrayList<>();
        if (L1.isEmpty() || L2.isEmpty()) {
            return result;
        } else {
            for (int i = 0; i < L1.size(); i++) {
                for (int j = 0; j < L2.size(); j++) {
                    if (L1.get(i) == L2.get(j)) {
                        result.add(L1.get(i));
                    }
                }
            }
            return result;
        }
    }

    public static int countOccurrenceOfC(List<String> L, char c) {
        int sum = 0;
        for (int i = 0; i < L.size(); i++) {
            for (int j = 0; j < L.get(i).length(); j++) {
                if (L.get(i).charAt(j) == c) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    // Tast3
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> somemap = new TreeMap<>();
        int n = 1;
        char c = 'a';
        for (int i = 0; i < 26; i++) {
            somemap.put(c, n);
            c += 1;
            n += 1;
        }
        return somemap;
    }

    public static Map<Integer, Integer> squares(List<Integer> L) {
        Map<Integer, Integer> somemap = new TreeMap<>();
        for (int i = 0; i < L.size(); i++) {
            somemap.put(L.get(i), L.get(i) * L.get(i));
        }
        return somemap;
    }

    public static Map<String, Integer> countword(List<String> L) {
        Map<String, Integer> somemap = new TreeMap<>();
        String word;
        int times = 0;
        for (int i = 0; i < L.size(); i++) {
            word = L.get(i);
            for (int j = 0; j < L.size(); j++) {
                if (Objects.equals(word, L.get(j))) {
                    times += 1;
                }
            }
            somemap.put(word, times);
            times = 0;
        }
        return somemap;
    }
}