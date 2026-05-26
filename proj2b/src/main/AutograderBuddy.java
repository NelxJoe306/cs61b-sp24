package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import graph.HyponymsHelper;

public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        NGramMap ngm = new NGramMap(wordFile, countFile);

        HyponymsHelper hhp = new HyponymsHelper(hyponymFile, synsetFile);

        return new HyponymsHandler(hhp);
    }
}
