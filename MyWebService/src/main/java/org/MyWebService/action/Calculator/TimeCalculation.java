package org.MyWebService.action.Calculator;

import org.apache.log4j.Logger;

public class TimeCalculation {

    private static final Logger log = Logger.getLogger(TimeCalculation.class);

    public static double calculateTime(int distance, int speed) {
        double time = (double) distance / speed;
        log.info("time: " + time);
        return time;
    }

}
