package org.MyWebService.action.validation;

import org.MyWebService.exception.ServerErrorException;

public class InputValidator {
    public boolean isValidInput(int distance, int speed) {
        boolean isValid = true;
        if(distance<=0 || speed<=0){
            isValid = false;
        }
        return isValid;
    }
}
