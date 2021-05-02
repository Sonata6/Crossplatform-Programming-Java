package org.mywebservice.service;

import org.mywebservice.cache.TimeCache;
import org.mywebservice.entity.TimeDto;
import org.mywebservice.entity.SpeedDistanceDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TimeCalculationService {

    private static final Logger log = Logger.getLogger(TimeCalculationService.class);


    public TimeDto calculateTimeUsingCache(SpeedDistanceDto speedDistanceDto) {
        if(TimeCache.getFromCache(speedDistanceDto) != null){
            return TimeCache.getFromCache(speedDistanceDto);
        }
        log.info("calculating time's value");
        return new TimeDto(speedDistanceDto.getDistance() / speedDistanceDto.getSpeed());
    }

    public static TimeDto calculateTime(int distance, int speed) {
        TimeDto time = new TimeDto( distance / speed);
        log.info("time: " + time);

        return time;
    }
    
    public static TimeDto calculateTime(SpeedDistanceDto speedDistanceDto) {
        TimeDto time = new TimeDto( speedDistanceDto.getDistance() / speedDistanceDto.getSpeed());
        log.info("time: " + time);
        return time;
    }

}
