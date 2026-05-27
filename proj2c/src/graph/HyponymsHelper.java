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
            for (int id : IDsOfWord) {
                List<Integer> hypoIDs = grpTraversalOfIDs(id);
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
            for (int id : IDsOfWord) {
                List<Integer> hypoIDs = grpInTraversalOfIDs(id);
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


    //return hyponyms' IDs
    //idea: getchildren to get nextHypos, add
    //if no hyponyms, return empty
    public List<Integer> grpTraversalOfIDs(int id) {
        List<Integer> hypoIDL = new ArrayList<>(List.of(id));
        List<Integer> childrenList = grp.getChildren(id);
        if (childrenList.isEmpty()) {
            return hypoIDL;
        } else {
            for (int i : childrenList) {
                hypoIDL.addAll(grpTraversalOfIDs(i));
            }
        }
        return hypoIDL;
    }

    public List<Integer> grpInTraversalOfIDs(int id) {
        List<Integer> hypoIDL = new ArrayList<>(List.of(id));
        Integer ancestorsID = grp.getAncestors(id);
        if (ancestorsID < 0) {
            return hypoIDL;
        } else {
            hypoIDL.addAll(grpInTraversalOfIDs(ancestorsID));
        }
        return hypoIDL;
    }

    //sorts the given list in alphabetical order
    private void alphaSort(List<String> l) {
        Collections.sort(l);
    }

}
