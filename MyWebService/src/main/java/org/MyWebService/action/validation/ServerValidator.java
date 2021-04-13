package org.MyWebService.action.validation;

import org.MyWebService.exception.ServerErrorException;

public class ServerValidator {
    public boolean isValidForServer(int distance, int speed) {
        boolean isValid = true;
        if(distance<=0 || speed<=0){
            isValid = false;
        }
        return isValid;
    }
}
