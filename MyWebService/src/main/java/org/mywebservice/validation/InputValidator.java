package org.mywebservice.validation;

import org.mywebservice.entity.SpeedDistanceDto;

public class InputValidator {

    public boolean isValidInput(int distance, int speed) {
        boolean isValid = true;
        if(distance<=0 || speed<=0){
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidInput(SpeedDistanceDto speedDistanceDto) {
        boolean isValid = true;
        if(speedDistanceDto.getDistance()<=0 || speedDistanceDto.getSpeed()<=0){
            isValid = false;
        }
        return isValid;
    }
}
