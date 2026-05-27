package main;

import browser.NgordnetServer;
import graph.HyponymsHelper;
import ngrams.NGramMap;
import org.slf4j.LoggerFactory;

public class Main {
    static {
        LoggerFactory.getLogger(Main.class).info("\033[1;38mChanging text color to white");
    }
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();
        
        /* The following code might be useful to you.
*/
        String wordFile = "./proj2c/data/ngrams/top_14377_words.csv";
        String countFile = "./proj2c/data/ngrams/total_counts.csv";
        NGramMap ngm = new NGramMap(wordFile, countFile);

        String synsetFile = "./proj2c/data/wordnet/synsets.txt";
        String hyponymFile = "./proj2c/data/wordnet/hyponyms.txt";
        HyponymsHelper hhp = new HyponymsHelper(hyponymFile, synsetFile);

        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());
        hns.register("hyponyms", new HyponymsHandler(hhp, ngm));

        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}
