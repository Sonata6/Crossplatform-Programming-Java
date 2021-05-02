package org.mywebservice.cache;

import org.apache.log4j.Logger;
import org.mywebservice.entity.SpeedDistanceDto;
import org.mywebservice.entity.TimeDto;
import org.mywebservice.repository.SpeedDistanceDtoRepository;
import org.mywebservice.repository.TimeDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class TimeCache {

    private static SpeedDistanceDtoRepository speedDistanceDtoRepository;
    private static TimeDtoRepository timeDtoRepository;

    @Autowired
    SpeedDistanceDtoRepository speedDistanceDtoRepositoryAuto;
    @Autowired
    TimeDtoRepository timeDtoRepositoryAuto;

    private static final Map<SpeedDistanceDto, TimeDto> cache = new HashMap<>();
    private static final org.apache.log4j.Logger logger = Logger.getLogger(TimeCache.class);


    @PostConstruct
    private void init() {
        this.speedDistanceDtoRepository = speedDistanceDtoRepositoryAuto;
        this.timeDtoRepository = timeDtoRepositoryAuto;
    }

    @PostConstruct
    private void initCache(){
        long counter = 1;
        while (speedDistanceDtoRepository.existsById(counter++)) {
            cache.put(speedDistanceDtoRepository.findById(counter - 1).get(), timeDtoRepository.findById(counter - 1).get());
        }
    }

    public static TimeDto getFromCache(SpeedDistanceDto key){
        logger.info("getting value from cache");
        return cache.get(key);
    }

    public static void putInCache(SpeedDistanceDto key, TimeDto value){
        logger.info("putting calculated value in cache");
        cache.put(key,value);
        speedDistanceDtoRepository.save(key);
        timeDtoRepository.save(value);
    }
}