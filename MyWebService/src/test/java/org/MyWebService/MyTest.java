package org.MyWebService;

import org.MyWebService.action.Calculator.TimeCalculation;
import org.MyWebService.exception.ServerErrorException;
import org.junit.Assert;
import org.junit.Test;

public class MyTest {

    @Test
    public void simpleLogicTest1() throws ServerErrorException, ServerErrorException {
        double time = TimeCalculation.calculateTime(88, 10);
        Assert.assertEquals(8.8, time, 0.01);
    }

    @Test
    public void simpleLogicTest2() throws ServerErrorException, ServerErrorException {
        double time = TimeCalculation.calculateTime(88, 10);
        Assert.assertNotEquals(7.77, time);
    }
}
