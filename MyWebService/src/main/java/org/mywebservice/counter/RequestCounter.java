package org.mywebservice.counter;

import org.apache.log4j.Logger;

public class RequestCounter {

    private static final Logger logger = Logger.getLogger(RequestCounter.class);
    private static int counter = 0;
    public static synchronized void inc(){
        counter++;
        logger.info("counter incremented, current value: " + counter);
    }
}
