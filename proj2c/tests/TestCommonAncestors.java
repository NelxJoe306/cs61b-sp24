import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import graph.HyponymsHelper;
import graph.wordNetGraph;
import main.AutograderBuddy;
import main.HyponymsHandler;
import ngrams.NGramMap;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class TestCommonAncestors {
    public static final String WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String LARGE_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    /** This is an example from the spec for a common-ancestors query on the word "adjustment".
     * You should add more tests for the other spec examples! */
    @Test
    public void testSpecAdjustment() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("adjustment");

        NgordnetQuery nq = new NgordnetQuery(words, 2000, 2020, 0, NgordnetQueryType.ANCESTORS);
        String actual = studentHandler.handle(nq);
        String expected = "[adjustment, alteration, event, happening, modification, natural_event, occurrence, occurrent]";
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void testInvsMap() {
        wordNetGraph g = new wordNetGraph("./data/wordnet/hypoTest.txt");
        System.out.print(g.invsList.toString()+"\n");
        Set<Integer> v = new LinkedHashSet<>();
        HyponymsHelper hhp = new HyponymsHelper("./data/wordnet/hypoTest.txt", "./data/wordnet/synsetTest.txt");
        System.out.print(hhp.grpInTraversalOfIDs(6, v));
        System.out.print(hhp.grpInTraversalOfIDs(0, v));
        }
    @Test
    public void testInTra() {
        String hypof = "./data/wordnet/hyponyms11.txt";
        String synF = "./data./wordnet/synsets11.txt";
        HyponymsHelper hhp = new HyponymsHelper(hypof, synF);
        List<String> l = hhp.graphInTraversal(List.of("demotion"));
        assertThat(l).containsExactly("action", "change",  "demotion");
        l = hhp.graphInTraversal(List.of("demotion","change"));
        assertThat(l).containsExactly("action", "change");
        assertThat(l).isInOrder();
    }

    @Test
    public void testKisNotZero() {
        NGramMap ngr = new NGramMap(WORDS_FILE, TOTAL_COUNTS_FILE);
        HyponymsHelper hhp = new HyponymsHelper("./data/wordnet/hypoTest.txt", "./data/wordnet/synsetTest.txt");
        HyponymsHandler hh = new HyponymsHandler(hhp, ngr);

        List<String> l = hh.handleK(Collections.singletonList("airport"),1, 2000,2001);
        System.out.print(l.toString());
    }
}
