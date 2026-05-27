
import graph.IDtoWord;
import graph.wordNetGraph;
import graph.wordnet_wordToIDs;
import graph.HyponymsHelper;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class TestGraph {
    @Test
    public void testWTI(){
        String filename = "./data/wordnet/synsets14.txt";
        wordnet_wordToIDs some = new wordnet_wordToIDs(filename);
        List<Integer> somel = some.get("change");
        assertThat(somel).containsExactly(2,8);

    }

    @Test
    public void testGetkeys(){
        String filename = "./data/wordnet/synsets14.txt";
        wordnet_wordToIDs some = new wordnet_wordToIDs(filename);
        Set<String> keysl = some.getKeys();
        assertThat(keysl).contains("change");
    }

    @Test
    public void testGetword(){
        String filename = "./data/wordnet/synsets14.txt";
        wordnet_wordToIDs some = new wordnet_wordToIDs(filename);
        IDtoWord somes = new IDtoWord(some);
        assertThat(somes.get(2)).containsExactly("change", "modification", "alteration");
    }

    @Test
    public void tetsGraphConst(){
        String filename = "./data/wordnet/hyponyms11.txt";
        wordNetGraph some = new wordNetGraph(filename);
        List<Integer> l = some.getChildren(5);
        assertThat(l).containsExactly(6,7);
    }

    @Test
    public void testHypoHelper() {
        String hypof = "./data/wordnet/hyponyms11.txt";
        String synF = "./data./wordnet/synsets11.txt";
        HyponymsHelper hhp = new HyponymsHelper(hypof, synF);
        List<String> someL = hhp.graphTraversal(List.of("action"));
        assertThat(someL).containsExactly("action","alternate","change", "demotion");
    }

    @Test
    public void testTraId() {
        String hypof = "./scr/data/wordnet/hyponyms11.txt";
        String synF = "./scr/data/wordnet/synsets11.txt";
        HyponymsHelper hhp = new HyponymsHelper(hypof, synF);
        Set<Integer> v = new LinkedHashSet<>();
        List<Integer> l = hhp.grpTraversalOfIDs(0, v);
        assertThat(l).containsExactly(0,1,2);

        l = hhp.grpTraversalOfIDs(5, v);
        assertThat(l).containsExactly(5,6,7);
    }
    @Test
    public void testTra() {
        String hypof = "./data/wordnet/hyponyms11.txt";
        String synF = "./data./wordnet/synsets11.txt";
        HyponymsHelper hhp = new HyponymsHelper(hypof, synF);
        List<String> l = hhp.graphTraversal(List.of("action"));
        assertThat(l).containsExactly("action", "change", "alternate", "demotion");
        assertThat(l).isInOrder();

        l = hhp.graphTraversal(List.of("increase"));
        assertThat(l).containsExactly("augmentation","increase","jump","leap");
        assertThat(l).isInOrder();
    }

    @Test
    public void testTraWords() {
        String hypof = "./data/wordnet/hyponyms14.txt";
        String synF = "./data./wordnet/synsets14.txt";
        HyponymsHelper hhp = new HyponymsHelper(hypof, synF);
        List<String> l = hhp.graphTraversal(List.of("happening", "adjustment"));
        assertThat(l).containsExactly("conversion", "modification", "adjustment", "alteration", "mutation");
    }
}
