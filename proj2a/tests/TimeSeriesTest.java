import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }


    @Test
    public void testyear() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        List<Integer> L = catPopulation.years();
        assertThat(L).containsExactly(1991,1992,1994);
    }

    @Test
    public void testTimeseries() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);
        catPopulation.put(1993, 0.0);
        catPopulation.put(1995, 2.0);

        TimeSeries t = new TimeSeries(catPopulation, 1992, 1994);
        List<Integer> L = t.years();
        assertThat(L).containsExactly(1992,1993,1994);
    }
    @Test
    public void testdaTA() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);
        catPopulation.put(1993, 0.0);
        catPopulation.put(1995, 2.0);

        TimeSeries t = new TimeSeries(catPopulation, 1992, 1994);
        List<Double> L = t.data();
        assertThat(L).containsExactly(100.0, 0.0, 200.0);
    }

    @Test
    public void testPlus() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);
        catPopulation.put(1993, 0.0);
        catPopulation.put(1995, 2.0);

        TimeSeries t = new TimeSeries(catPopulation, 1992, 1994);
        TimeSeries ts = catPopulation.plus(t);
        List<Double> L = ts.data();
        assertThat(L).containsExactly(0.0,200.0, 0.0, 400.0, 2.0);

        TimeSeries emptyT = new TimeSeries();
        emptyT = emptyT.plus(emptyT);
        assertThat(emptyT).isEmpty();
    }

    @Test
    public void testDivided() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);
        catPopulation.put(1993, 0.0);
        catPopulation.put(1995, 2.0);

        // test divided by null
        TimeSeries t = new TimeSeries();
        TimeSeries ts = catPopulation.dividedBy(t);
        List<Double> L = ts.data();
        assertThat(L).containsExactly(0.0, 100.0, 0.0, 200.0, 2.0);

        // test divided by (1992 -> 2.0, 1994 -> 5)
        TimeSeries t1 = new TimeSeries(catPopulation, 1992, 1994);
        t1.remove(1993);
        TimeSeries ts1 = catPopulation.dividedBy(t1);
        List<Double> L1 = ts1.data();
        assertThat(L1).containsExactly(0.0, 1.0, 0.0, 1.0, 2.0);

        TimeSeries cat = new TimeSeries();
        TimeSeries t2 = cat.dividedBy(t1);
        assertThat(t2).isEmpty();


    }
} 