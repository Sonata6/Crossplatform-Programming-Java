package org.MyWebService.action.service;

import org.MyWebService.action.Calculator.TimeCalculation;
import org.MyWebService.action.hash.TimeHash;
import org.MyWebService.action.validation.InputValidator;
import org.MyWebService.action.validation.ServerValidator;
import org.MyWebService.entity.ResultDto;
import org.MyWebService.exception.BadRequestException;
import org.MyWebService.exception.ServerErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TimeService implements Service<ResultDto, Integer> {

    @Autowired
    private TimeHash hash;

    private static Logger log = LogManager.getLogger();
    @Override
    public ResultDto doService(Integer distance, Integer speed) throws BadRequestException, ServerErrorException {
        if (!(new InputValidator().isValidInput(distance, speed))) {
            log.error("distance or speed <= 0");
            throw new ServerErrorException("distance or speed less then 1");
        }
        if (!(new ServerValidator().isValidForServer(distance, speed))) {
            log.error("distance or speed so large");
            throw new ServerErrorException("distance or speed more than we can process");
        }
        Integer num = distance/speed;
        if (hash.isAlreadyHashed(num)) {
            return hash.getParameters(num);
        }
        TimeCalculation timeCalculation = new TimeCalculation();
        double time = timeCalculation.calculateTime(distance, speed);
        ResultDto dto = new ResultDto(time);
        hash.addToMao(num, dto);
        return dto;
    }
}