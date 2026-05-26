package main;

import graph.*;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.ArrayList;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    HyponymsHelper hhp;

    public HyponymsHandler(HyponymsHelper helper) {
        this.hhp = helper;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> s = q.words();
        List<String> l = new ArrayList<>();
        l = hhp.graphTraversal(s);

        return l.toString();
    }
}
