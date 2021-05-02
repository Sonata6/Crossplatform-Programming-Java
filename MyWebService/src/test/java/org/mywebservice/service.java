package org.mywebservice;

import org.mywebservice.creator.TimeDtoCreator;
import org.mywebservice.exception.BadRequestException;

import org.mywebservice.entity.TimeDto;
import org.mywebservice.entity.SpeedDistanceDto;
import org.mywebservice.exception.ServerErrorException;
import org.junit.Assert;
import org.junit.Test;

public class service {
    public static class TimeCalculationServiceTest {

        private static SpeedDistanceDto speedDistanceDto = new SpeedDistanceDto(88, 10);
        private static TimeDtoCreator timeDtoCreator = new TimeDtoCreator();
        @Test
        public void calculateTimeEqualsTest() throws ServerErrorException, ServerErrorException, BadRequestException {
            TimeDto timeDto = timeDtoCreator.createTimeDto(speedDistanceDto);
            Assert.assertEquals(8.8, timeDto.getTime(), 0.01);
        }

        @Test
        public void calculateTimeNotEqualsTest() throws ServerErrorException, ServerErrorException, BadRequestException {
            TimeDto timeDto = timeDtoCreator.createTimeDto(speedDistanceDto);
            Assert.assertNotEquals(7.7, timeDto.getTime());
        }
    }
}
