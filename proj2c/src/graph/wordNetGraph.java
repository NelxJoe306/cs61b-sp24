package graph;

import edu.princeton.cs.algs4.In;

import java.util.*;


public class wordNetGraph {
    private final Map<Integer, List<Integer>> adjList;
    public final Map<Integer, List<Integer>> invsList;

    public wordNetGraph(String hyponymsFilename) {
        adjList = new HashMap<>();
        In in = new In(hyponymsFilename);
        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            int rootID = Integer.parseInt(splitLine[0]);
            List<Integer> hypoList = new ArrayList<>();
            for (String n : splitLine) {
                int id = Integer.parseInt(n);
                if (id != rootID) {
                    hypoList.add(id);
                }
            }
            if (adjList.containsKey(rootID)) {
                adjList.get(rootID).addAll(hypoList);
            } else {
                adjList.put(rootID, hypoList);
            }
        }
        this.invsList = invs(adjList);
    }

    // return the hypoList; returns emptyList if there's no hypos
    public List<Integer> getChildren(int v) {
        return adjList.getOrDefault(v, new ArrayList<>());
    }


    public List<Integer> getAncestors(int v) {
        return invsList.getOrDefault(v, List.of(-1));
    }

    // build an inverse map
    private Map<Integer, List<Integer>> invs(Map<Integer, List<Integer>> map) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (int l : map.keySet()) {
            for (int n : map.get(l)) {
                if (!result.containsKey(n)) {
                    List<Integer> s = new ArrayList<>();
                    s.add(l);
                    result.put(n, s);
                } else {
                    result.get(n).add(l);
                }
            }
        }
        return result;
    }
}

