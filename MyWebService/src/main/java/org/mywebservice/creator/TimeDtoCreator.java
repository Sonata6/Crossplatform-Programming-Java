package org.mywebservice.creator;

import org.mywebservice.service.TimeCalculationService;
import org.mywebservice.cache.TimeCache;
import org.mywebservice.validation.InputValidator;
import org.mywebservice.validation.ServerValidator;
import org.mywebservice.entity.TimeDto;
import org.mywebservice.entity.SpeedDistanceDto;
import org.mywebservice.exception.BadRequestException;
import org.mywebservice.exception.ServerErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;

@org.springframework.stereotype.Service
public class TimeDtoCreator {

    @Autowired

    private static Logger log = LogManager.getLogger();

    public TimeDto createTimeDto(SpeedDistanceDto speedDistanceDto) throws BadRequestException, ServerErrorException {
        if (!(new InputValidator().isValidInput(speedDistanceDto))) {
            log.error("distance or speed <= 0");
            throw new ServerErrorException("distance or speed less then 1");
        }
        if (!(new ServerValidator().isValidForServer(speedDistanceDto))) {
            log.error("distance or speed so large");
            throw new ServerErrorException("distance or speed more than we can process");
        }
        TimeCalculationService timeCalculationService = new TimeCalculationService();
        TimeDto timeDto = timeCalculationService.calculateTime(speedDistanceDto);
        TimeCache.putInCache(speedDistanceDto, timeDto);
        return timeDto;
    }
}