package org.mywebservice.validation;

import org.mywebservice.entity.SpeedDistanceDto;
import org.mywebservice.exception.ServerErrorException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerValidator {
    private static Logger logger = LogManager.getLogger();

    public boolean isValidForServer(int distance, int speed) throws ServerErrorException {
        boolean isValid = true;
        try {
            double result = (double) distance / speed;

        } catch (ArithmeticException e) {
            logger.log(Level.ERROR, "Result time is too big");
            throw new ServerErrorException("Time is too big");
        }

        return isValid;
    }

    public boolean isValidForServer(SpeedDistanceDto speedDistanceDto) throws ServerErrorException {
        boolean isValid = true;
        try {
            double result = (double) speedDistanceDto.getDistance() / speedDistanceDto.getSpeed();

        } catch (ArithmeticException e) {
            logger.log(Level.ERROR, "Result time is too big");
            throw new ServerErrorException("Time is too big");
        }

        return isValid;
    }
}
