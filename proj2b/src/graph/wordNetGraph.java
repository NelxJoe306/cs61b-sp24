package graph;

import edu.princeton.cs.algs4.In;

import java.util.*;


public class wordNetGraph {
    private final Map<Integer, List<Integer>> adjList;

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
    }

    // return the hypoList; returns emptyList if there's no hypos
    public List<Integer> getChildren(int v) {
        return adjList.getOrDefault(v, new ArrayList<>());
    }


}

