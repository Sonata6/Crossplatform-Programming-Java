package org.MyWebService;

import org.junit.Assert;
import org.junit.Test;

//import static org.springframework.test.util.AssertionErrors.assertEquals;

public class MyTest {

    @Test
    public void simpleLogicTest1() throws NotFoundException, ServerErrorException {
        double time = TimeCalculation.calculateTime(88, 10);
        Assert.assertEquals(8.8, time, 0.01);
    }

    @Test
    public void simpleLogicTest2() throws NotFoundException, ServerErrorException {
        double time = TimeCalculation.calculateTime(88, 10);
        Assert.assertNotEquals(7.77, time);
    }
}
