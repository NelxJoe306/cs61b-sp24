package graph;

import java.util.*;

public class HyponymsHelper {
    private final wordNetGraph grp;
    private final wordnet_wordToIDs WTImap;
    private final IDtoWord ITWmap;

    public HyponymsHelper(String hypoFilename, String synsetFilename) {
        grp = new wordNetGraph(hypoFilename);
        WTImap = new wordnet_wordToIDs(synsetFilename);
        ITWmap = new IDtoWord(WTImap);
    }

    //turn all hyponyms of the given word in order
    public List<String> graphTraversal(List<String> words) {
        Map<Integer, List<String>> hub = new TreeMap<>();
        int i = 0;
        List<String> returnList = new ArrayList<>();
        for (String word : words) {
            if (WTImap.get(word) == null) {
                return List.of();
            }
            List<Integer> IDsOfWord = WTImap.get(word);
            List<String> hypoWords = new ArrayList<>();
            Set<Integer> visited = new LinkedHashSet<>();
            for (int id : IDsOfWord) {
                List<Integer> hypoIDs = grpTraversalOfIDs(id, visited);
                for (int j : hypoIDs) {
                    hypoWords.addAll(ITWmap.get(j));
                }
            }
            Set<String> t = new LinkedHashSet<>(hypoWords);
            hypoWords = new ArrayList<>(t);
            hub.put(i, hypoWords);
            i++;
        }
        returnList = hub.get(0);
        for (int n = 1; n < hub.size(); n++) {
            returnList.retainAll(hub.get(n));
        }
        alphaSort(returnList);
        return returnList;
    }

    //turn all ancestors of the given word in order
    public List<String> graphInTraversal(List<String> words) {
        Map<Integer, List<String>> hub = new TreeMap<>();
        int i = 0;
        List<String> returnList = new ArrayList<>();
        for (String word : words) {
            if (WTImap.get(word) == null) {
                return List.of();
            }
            List<Integer> IDsOfWord = WTImap.get(word);
            List<String> hypoWords = new ArrayList<>();
            Set<Integer> visited = new LinkedHashSet<>();
            for (int id : IDsOfWord) {
                List<Integer> ancestorIDs = new ArrayList<>();
                if (!visited.contains(id)) {
                    ancestorIDs = grpInTraversalOfIDs(id, visited);
                    for (int j : ancestorIDs) {
                        hypoWords.addAll(ITWmap.get(j));
                    }
                    visited.add(id);
                }
            }
            Set<String> t = new LinkedHashSet<>(hypoWords);
            hypoWords = new ArrayList<>(t);
            hub.put(i, hypoWords);
            i++;
        }
        returnList = hub.get(0);
        for (int n = 1; n < hub.size(); n++) {
            returnList.retainAll(hub.get(n));
        }
        alphaSort(returnList);
        return returnList;
    }


    //return hyponyms' IDs
    //idea: getchildren to get nextHypos, add
    //if no hyponyms, return empty
    public List<Integer> grpTraversalOfIDs(int id, Set<Integer> v) {
        List<Integer> hypoIDL = new ArrayList<>(List.of(id));
        List<Integer> childrenList = grp.getChildren(id);
        if (childrenList.isEmpty()) {
            return hypoIDL;
        } else {
            for (int i : childrenList) {
                if (!v.contains(i)) {
                    v.add(i);
                    hypoIDL.addAll(grpTraversalOfIDs(i, v));
                }
            }
        }
        return hypoIDL;
    }

    public List<Integer> grpInTraversalOfIDs(int id, Set<Integer> v) {
        List<Integer> hypoIDL = new ArrayList<>(List.of(id));
        List<Integer> ancestorsID = grp.getAncestors(id);
        for (int i : ancestorsID) {
            if (i < 0) {
                return hypoIDL;
            } else {
                if (!v.contains(i)) {
                    v.add(i);
                    hypoIDL.addAll(grpInTraversalOfIDs(i, v));
                }
            }
        }
        return hypoIDL;
    }

    //sorts the given list in alphabetical order
    private void alphaSort(List<String> l) {
        Collections.sort(l);
    }

}
