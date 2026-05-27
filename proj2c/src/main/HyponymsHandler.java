package main;

import browser.NgordnetQueryType;
import graph.*;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;

public class HyponymsHandler extends NgordnetQueryHandler {
    HyponymsHelper hhp;
    NGramMap neededMap;

    public HyponymsHandler(HyponymsHelper helper, NGramMap ngr) {
        this.neededMap = ngr;
        this.hhp = helper;
    }

    //handle with k!=0
    public List<String> handleK(List<String> words, int k, int sy, int ey) {
        List<String> l = hhp.graphTraversal(words);
        if (k == 0) {
            return l;
        } else {
            int n = min(k, l.size());
            Map<String, TimeSeries> m = new HashMap<>();
            Map<String, Integer> nums = new HashMap<>();
            for (String s : l) {
                if (neededMap.countHistory(s, sy, ey) == null) {
                    continue;
                }
                TimeSeries t = neededMap.countHistory(s, sy, ey);
                if (t.years().isEmpty()) {
                    continue;
                }
                m.put(s, t);
            }
            for (String word : m.keySet()) {
                nums.put(word, addTS(m.get(word)));
            }
            return getTopNKeys(nums, n);
        }

    }

    //handle with k!=0
    public List<String> handleKOfAn(List<String> words, int k, int sy, int ey) {
        List<String> l = hhp.graphInTraversal(words);
        if (k == 0) {
            return l;
        } else {
            int n = min(k, l.size());
            Map<String, TimeSeries> m = new HashMap<>();
            Map<String, Integer> nums = new HashMap<>();
            for (String s : l) {
                if (neededMap.countHistory(s, sy, ey) == null) {
                    continue;
                }
                TimeSeries t = neededMap.countHistory(s, sy, ey);
                if (t.years().isEmpty()) {
                    continue;
                }
                m.put(s, t);
            }
            for (String word : m.keySet()) {
                nums.put(word, addTS(m.get(word)));
            }
            return getTopNKeys(nums, n);
        }
    }
    public List<String> getTopNKeys(Map<String, Integer> map, int n) {
        List<String> l = sortKeysByValueDescThenKeyAsc(map);
        return l.stream().limit(n).toList();
    }

    public static List<String> sortKeysByValueDescThenKeyAsc(Map<String, Integer> map) {
        return map.entrySet().stream().
                sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()).
                        thenComparing(Map.Entry.comparingByKey())).
                map(Map.Entry::getKey).
                collect(Collectors.toList());
    }

    //return the total number of the TS
    public Integer addTS(TimeSeries ts) {
        int tot = 0;
        for (double n : ts.years()) {
            tot += ts.get((int) n);
        }
        return tot;
    }

    /**@return the common ancestors,
     *take  List<String>
     **/
    public List<String> findAncestor(List<String> words) {

        return List.of();
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> s = q.words();
        NgordnetQueryType t = q.ngordnetQueryType();
        int k = q.k();
        int sy = q.startYear();
        int ey = q.endYear();
        if (t == NgordnetQueryType.ANCESTORS) {
            List<String> l = this.handleKOfAn(s, k, sy, ey);
            return l.toString();
        } else {
            List<String> l = this.handleK(s, k, sy, ey);
            return l.toString();
        }
    }
}
