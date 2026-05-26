package graph;

import java.util.*;


public class IDtoWord {
    private final Map<Integer, List<String>> ITWmap;

    public IDtoWord(wordnet_wordToIDs WTImap) {
        ITWmap = new HashMap<>();
        Set<String> keys = WTImap.getKeys();
        for (String word : keys) {
            List<Integer> IDs = WTImap.get(word);
            for (Integer id : IDs) {
                if (!ITWmap.containsKey(id)) {
                    List<String> words = new ArrayList<>();
                    words.add(word);
                    ITWmap.put(id, words);
                } else {
                    ITWmap.get(id).add(word);
                }
            }
        }
    }

    // given ID, return the word
    public List<String> get(int id) {
        return ITWmap.get(id);
    }

}
