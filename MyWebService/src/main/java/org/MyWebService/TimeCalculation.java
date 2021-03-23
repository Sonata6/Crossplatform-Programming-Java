package org.MyWebService;

import org.apache.log4j.Logger;

public class TimeCalculation {

    private static final Logger log = Logger.getLogger(TimeCalculation.class);


//    public TimeCalculation(ResultDto resultDto){
//        log.debug("Start of calculating time method");
//
//        double time = resultDto.getDistance()/resultDto.getSpeed();
//        resultDto.setTime(time);
//    }


    public static double calculateTime(double distance, double speed) throws NotFoundException, ServerErrorException {
        if(distance<=0 || speed<=0){
            log.error("distance or speed <= 0");
            throw new NotFoundException("distance or speed less then 1");
        }
        if(distance > 1000000000 || speed > 1000000000){
            log.error("distance or speed so large");
            throw new ServerErrorException("distance or speed more than we can process");
        }
        double time = distance / speed;
        return time;
    }

}
