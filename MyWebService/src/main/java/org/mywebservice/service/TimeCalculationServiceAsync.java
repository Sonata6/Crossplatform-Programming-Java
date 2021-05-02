package org.mywebservice.service;

import org.apache.log4j.Logger;
import org.mywebservice.entity.SpeedDistanceDto;
import org.mywebservice.entity.TimeDto;
import org.mywebservice.repository.TimeDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TimeCalculationServiceAsync {

    private static final Logger logger = Logger.getLogger(TimeCalculationServiceAsync.class);
    @Autowired
    private TimeCalculationService timeCalculationService;

    @Autowired
    private TimeDtoRepository timeDtoRepository;

    public void calculateAsync(Long id, SpeedDistanceDto speedDistanceDto){
        logger.info("Starting new thread");
        CompletableFuture.supplyAsync(()-> timeCalculationService.calculateTimeUsingCache(speedDistanceDto))
                .thenApply(x-> timeDtoRepository.save(new TimeDto(x.getTime())));
    }

}
