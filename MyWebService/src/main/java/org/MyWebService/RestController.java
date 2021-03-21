package org.MyWebService;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("task")
public class RestController {

    private static final Logger log = Logger.getLogger(TimeCalculation.class);

    @GetMapping
    public ResultDto timeCalculate(@RequestParam int distance, @RequestParam int speed) throws NotFoundException, ServerErrorException {
        log.info("Get Request param from URL");
        var time = TimeCalculation.calculateTime(distance, speed);
        var result = new ResultDto(time);
        log.info("Return task answer");
        return result;
    }

}