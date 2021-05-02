package org.mywebservice.controller;

import org.mywebservice.entity.SpeedDistanceDto;
import org.mywebservice.entity.TimeDto;
import org.mywebservice.entity.TimeDtoListStat;
import org.mywebservice.exception.BadRequestException;
import org.mywebservice.exception.ServerErrorException;
import org.mywebservice.repository.TimeDtoListStatRepository;
import org.mywebservice.repository.TimeDtoRepository;
import org.mywebservice.service.TimeCalculationService;
import org.apache.log4j.Logger;
import org.mywebservice.service.TimeCalculationServiceAsync;
import org.mywebservice.service.TimeCalculationServiceStreamApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.mywebservice.counter.RequestCounter;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/timeValue")
public class MyRestController {

    @Autowired
    TimeCalculationService timeCalculationService;

    @Autowired
    TimeCalculationServiceStreamApi timeCalculationServiceStreamApi;

    @Autowired
    TimeCalculationServiceAsync timeCalculationServiceAsync;

    @Autowired
    TimeDtoRepository timeDtoRepository;

    @Autowired
    TimeDtoListStatRepository timeDtoListStatRepository;


    private static final Logger logger = Logger.getLogger(MyRestController.class);
    //Get
    @GetMapping("/count")
    public TimeDto calculateTime(@RequestParam(value = "distance") int distance,
                                 @RequestParam(value = "speed") int speed)
            throws BadRequestException {
        if (speed <= 0 || distance <= 0) throw new BadRequestException();
        SpeedDistanceDto speedDistanceDto = new SpeedDistanceDto(speed, distance);
        RequestCounter.inc();
        logger.info("return GTO");
        return timeCalculationService.calculateTime(speedDistanceDto);
    }

    //Post
    @PostMapping("/countViaJSONList")
    public List<TimeDto> calculateTimeBulk(@RequestBody  List<SpeedDistanceDto> speedDistanceDtoList){
        RequestCounter.inc();
        return timeCalculationServiceStreamApi.calculateTimeList(speedDistanceDtoList);
    }

    @PostMapping("/countViaJSONListAsync")
    public void calculateTimeAsync(@RequestBody  List<SpeedDistanceDto> speedDistanceDtoList,
                                   @RequestParam(value = "id") Long id){
        RequestCounter.inc();
        CompletableFuture.supplyAsync(()-> timeCalculationServiceStreamApi
                .calculateTimeListStat(speedDistanceDtoList))
                .thenApply((x)-> timeDtoListStatRepository
                        .save((x)));
    }

    @PostMapping("/countViaJSONListStat")
    public TimeDtoListStat calculateTimeBulkStat(@RequestBody List<SpeedDistanceDto> speedDistanceDtoList){
        RequestCounter.inc();
        return timeCalculationServiceStreamApi.calculateTimeListStat(speedDistanceDtoList);
    }

    //Async
    @GetMapping("/countAsync")
    public void calculateTimeAsync(@RequestParam(value = "speed") int speed,
                                   @RequestParam(value = "distance") int distance,
                                   @RequestParam(value = "id") Long id) throws BadRequestException, ServerErrorException {
        RequestCounter.inc();
        if(timeDtoRepository.existsById(id)) throw new ServerErrorException();
        if(distance < 0 || speed < 0 || id < 0) throw new BadRequestException();
        timeCalculationServiceAsync.calculateAsync(id, new SpeedDistanceDto(distance, speed));
    }

    @GetMapping("/getTimeById")
    public TimeDto getTimeById(@RequestParam(value = "id") Long id) throws ServerErrorException {
        RequestCounter.inc();
        if(timeDtoRepository.existsById(id))
            return timeDtoRepository.findById(id).get();
        else throw new ServerErrorException();
    }

    @GetMapping("/getTimeByIdList")
    public TimeDtoListStat getTimeIdByList(@RequestParam(value = "id") Long id)  {
        RequestCounter.inc();
        return timeDtoListStatRepository.findById(id).get();
    }

}