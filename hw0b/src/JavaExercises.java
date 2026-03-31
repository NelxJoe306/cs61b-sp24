
import java.util.*;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import static java.lang.System.in;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(Enclosed.class)
public class JavaExercises {
    public int[] makeDice(){
        return new int[]{1,2,3,4,5,6};
    }
    // Task1
    public String[] takeOrder(String name){
        if (Objects.equals(name, "Ergun")) {
            return new String[]{"beyti","pizza","hamburger","tea"};

        } else if (Objects.equals(name,"Eric")) {
            return new String[]{"sushi","pasta","avocado","coffee"};
        }else{
            return new String[3];
        }
    }
    public int finMinMax(int[] arr){
        int L = arr.length;
        int max = arr[0],min = arr[0];
        for(int i=1;i<L;i++){
            if (max < arr[i]){
                max = arr[i];
            }
            if (min>arr[i]){
                min = arr[i];
            }
        }
        return max-min;
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
        new hailstoneHelper(n,lst);
        return lst;
    }

    //Task2
    public static int sum(List<Integer> L){
        if (L.isEmpty()){
            return 0;
        }else{
            int sum = 0;
            for(int i = 0; i < L.size();i++){
                sum += L.get(i);
            }
            return sum;
        }
    }

    public static  List<Integer> evens(List<Integer> L){
        if (L.isEmpty()){
            return L;
        }else{
            List<Integer> EL = new ArrayList<>();
            for (int i = 0; i<L.size();i++){
                if (L.get(i) % 2 == 0){
                    EL.add(L.get(i));
                }
            }
            return EL;
        }
    }

    public static List<Integer> common(List<Integer> L1, List<Integer> L2){
        List<Integer> result = new ArrayList<>();
        if (L1.isEmpty()|| L2.isEmpty()){
            return result;
        }else{
            for (int i = 0; i<L1.size();i++){
                for (int j = 0; j<L2.size();j++){
                    if (L1.get(i)==L2.get(j)){
                        result.add(L1.get(i));
                    }
                }
            }
            return result;
        }
    }

    public static int countOccurrenceOfC(List<String> L, char c){
        int sum = 0;
        for (int i = 0; i< L.size();i++){
            for (int j = 0; j<L.get(i).length();j++){
                if (L.get(i).charAt(j) == c ){
                    sum += 1;
                }
            }
        }
        return sum;
    }

    // Tast3
    public static Map<Character,Integer> letterToNum(){
        Map<Character,Integer> somemap = new TreeMap<>();
        int n = 1;
        char c = 'a';
        for(int i = 0; i<26;i++){
            somemap.put(c,n);
            c += 1;
            n += 1;
        }
        return somemap;
    }
    public static Map<Integer,Integer> squares(List<Integer> L){
        Map<Integer,Integer> somemap = new TreeMap<>();
        for (int i = 0; i < L.size();i++){
            somemap.put(L.get(i),L.get(i)*L.get(i));
        }
        return somemap;
    }

    public static Map<String,Integer> countword(List<String> L){
        Map<String,Integer> somemap = new TreeMap<>();
        String word;
        int times = 0;
        for (int i = 0; i<L.size();i++){
            word = L.get(i);
            for (int j = 0; j < L.size();j++){
                if (Objects.equals(word, L.get(j))){
                    times += 1;
                }
            }
            somemap.put(word,times);
            times = 0;
        }
        return somemap;
    }




    public static class TaskTest{
        @Test
        public void testcountword(){
            List<String> L = List.of("dirty","deeds","done","dirt","cheap","cheap");
            int actualofdirt = countword(L).get("dirt");
            int actualofcheap = countword(L).get("cheap");
            int expectedofdirt = 1;
            int expectedofcheap = 2;
            assertThat(actualofcheap,equalTo(expectedofcheap));
            assertThat(actualofdirt,equalTo(expectedofdirt));
        }

        @Test
        public void testhailstone(){
            int c=3;
            List<Integer> d = hailstone(c);
            int actual = d.size();
            int expected = 8;
            assertThat(actual,equalTo(expected));
        }

        @Test
        public void testsum(){
            List<Integer> L = List.of(1,2,3,4,5);
            int actual = sum(L);
            assertThat(actual,equalTo(15));
        }

        @Test
        public void testevens(){
            List<Integer> L = List.of(1,2,3,5,6,7,8,9,24);
            List<Integer> expected = List.of(2,6,8,24);
            List<Integer> actual = evens(L);
            assertThat(actual,equalTo(expected));
        }

        @Test
        public void testcommon(){
            List<Integer> L1 = List.of(1,2,3,4,5);
            List<Integer> L2 = List.of(1,2,3,5,6,7,8,9,24);
            List<Integer> actual = common(L1,L2);
            List<Integer> expected = List.of(1,2,3,5);
            assertThat(actual,equalTo(expected));
        }

        @Test
        public void testcountOcurrenceOfC(){
            List<String> L = List.of("apple","jojo","aaahhhhhh");
            char c = 'a';
            int actual = countOccurrenceOfC(L,c);
            int expected = 4;
            assertThat(actual,equalTo(expected));
        }

        @Test
        public void testletterToNum(){
            char c='e';
            int actual = letterToNum().get(c);
            int expected = 5;
            assertThat(actual,equalTo(expected));
        }

        @Test
        public void testSquares(){
            List<Integer> L = List.of(1,2,3);
            List<Integer> actual = Collections.singletonList(squares(L).get(2));
            List<Integer> expected = List.of(4);
            assertThat(actual,equalTo(expected));


        }

    }

    public  void main() {

    }
}