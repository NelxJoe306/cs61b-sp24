package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private Map<String, TimeSeries> wordToTimeseries = new HashMap<>();
    private Map<Integer, Double> yearTonum = new TreeMap<>();
    In inOfWords;
    In inOfCounts;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        inOfCounts = new In(countsFilename);
        inOfWords = new In(wordsFilename);
        int i = 0;
        int j = 0;
        while (!inOfWords.isEmpty()) {
            i += 1;
            String nextLineFW =  inOfWords.readLine();
            String[] splitLine = nextLineFW.split("\t");
            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            Double n = Double.parseDouble(splitLine[2]);
            if (!wordToTimeseries.containsKey(word)) {
                TimeSeries ts = new TimeSeries();
                wordToTimeseries.put(word, ts);
                ts.put(year, n);
            } else {
                wordToTimeseries.get(word).put(year, n);
            }
        }
        while (!inOfCounts.isEmpty()) {
            j += 1;
            String nextLineFC = inOfCounts.readLine();
            String[] splitLine = nextLineFC.split(",");
            int year = Integer.parseInt(splitLine[0]);
            Double n = Double.parseDouble(splitLine[1]);
            yearTonum.put(year, n);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries t = new TimeSeries(this.wordToTimeseries.get(word), startYear, endYear);
        t = t;
        return t;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return this.wordToTimeseries.get(word);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries tot = new TimeSeries();
        for (int year : yearTonum.keySet()) {
            tot.put(year, yearTonum.get(year));
        }
        return tot;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!this.wordToTimeseries.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries tOfHistory = this.countHistory(word, startYear, endYear);
        return tOfHistory.dividedBy(this.totalCountHistory());
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!this.wordToTimeseries.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries tOfHistory = this.countHistory(word);
        return tOfHistory.dividedBy(this.totalCountHistory());
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        TimeSeries swt = new TimeSeries();
        for (String word : words) {
            if (this.wordToTimeseries.containsKey(word)) {
                TimeSeries t = this.weightHistory(word, startYear, endYear);
                swt = swt.plus(t);
            }
        }
        return swt;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries swt = new TimeSeries();
        for (String word : words) {
            if (!this.wordToTimeseries.containsKey(word)) {
                continue;
            } else {
                TimeSeries t = this.weightHistory(word);
                swt = swt.plus(t);
            }
        }
        return swt;
    }
}
