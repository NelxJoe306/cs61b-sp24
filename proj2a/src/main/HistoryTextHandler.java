package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

public class HistoryTextHandler extends NgordnetQueryHandler {

    NGramMap neededMap;
    public HistoryTextHandler(NGramMap map) {
        neededMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        StringBuilder response = new StringBuilder();

        for (String word : q.words()) {
            TimeSeries ts = neededMap.weightHistory(word, q.startYear(), q.endYear());

            response.append(word);
            response.append(": ");
            response.append(ts.toString());
            response.append("\n");
        }

        return response.toString();
    }

}
