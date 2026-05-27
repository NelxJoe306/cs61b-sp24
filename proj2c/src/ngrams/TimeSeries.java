package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        if (ts == null) {
            return;
        }
        this.putAll(ts.subMap(startYear, true, endYear, true));
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> l = new ArrayList<>(this.keySet());
        return l;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {

        List<Double> l = new ArrayList<>(this.values());
        return l;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries t = new TimeSeries();
        t.putAll(this);
        if (this.isEmpty() && ts.isEmpty()) {
            return t;
        } else if (this.isEmpty()) {
            return ts;
        } else if (ts.isEmpty()) {
            return t;
        }
        int startYear = Math.min(this.firstKey(), ts.firstKey());
        int endyear = Math.max(this.lastKey(), ts.lastKey());
        for (int i = startYear; i <= endyear; i++) {
            if (this.containsKey(i) && ts.containsKey(i)) {
                t.put(i, this.get(i) + ts.get(i));
            } else if (this.containsKey(i) && !ts.containsKey(i)) {
                t.put(i, this.get(i));

            } else if (!this.containsKey(i) && ts.containsKey(i)) {
                t.put(i, ts.get(i));
            }
        }

        return t;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries t = new TimeSeries();
        t.putAll(this);
        if (this.isEmpty() || ts.isEmpty()) {
            return t;
        }
        int startYear = Math.min(this.firstKey(), ts.firstKey());
        int endyear = Math.max(this.lastKey(), ts.lastKey());
        for (int i = startYear; i <= endyear; i++) {
            if (this.containsKey(i) && ts.containsKey(i)) {
                t.put(i, this.get(i) / ts.get(i));
            } else if (!this.containsKey(i) && ts.containsKey(i)) {
                continue;
            } else if (this.containsKey(i) && !ts.containsKey(i)) {
                throw new IllegalArgumentException();
            }
        }
        return t;
    }
}
