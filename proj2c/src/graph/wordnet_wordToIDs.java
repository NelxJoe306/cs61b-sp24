package graph;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class wordnet_wordToIDs {

    private final Map<String, List<Integer>> WTImap = new HashMap<>();

    //constructed with filename

    public  wordnet_wordToIDs(String wordnetFilename) {
        In in = new In(wordnetFilename);
        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            String[] words = splitLine[1].split(" ");
            for (String word : words) {
                int ID = Integer.parseInt(splitLine[0]);
                List<Integer> l = new ArrayList<>();
                if (!WTImap.containsKey(word)) {
                    l.add(ID);
                    WTImap.put(word, l);
                } else {
                    WTImap.get(word).add(ID);
                }

            }
        }
    }


    public List<Integer> get(String word) {
        return WTImap.get(word);
    }

    public Set<String> getKeys() {
        return WTImap.keySet();
    }
}
