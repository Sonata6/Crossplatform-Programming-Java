package org.MyWebService.action.service;

import org.MyWebService.exception.BadRequestException;
import org.MyWebService.exception.ServerErrorException;

public interface Service<T, E> {
    T doService(E distance, E speed) throws BadRequestException, ServerErrorException;
}
