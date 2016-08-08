package com.jmrodrigg.model.CJT;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class PageRange {

    public class Interval {

        public final Integer start               = null;
        public final Integer end                 = null;

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public final List<Interval> interval          = null;

    @Override
    public String toString() {
        return "PageRange{" +
                "interval=" + interval.toString() +
                '}';
    }
}
