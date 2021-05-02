package org.mywebservice.service;

import org.jetbrains.annotations.NotNull;
import org.mywebservice.entity.SpeedDistanceDto;
import org.mywebservice.entity.TimeDto;
import org.mywebservice.entity.TimeDtoListStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeCalculationServiceStreamApi {

    @Autowired
    TimeCalculationService timeCalculationService;

    public List<TimeDto> calculateTimeList(@NotNull List<SpeedDistanceDto> speedDistanceDtoList){
        return speedDistanceDtoList
                .stream()
                .map(x-> timeCalculationService.calculateTime(x))
                .collect(Collectors.toList());
    }

    public TimeDtoListStat calculateTimeListStat(List<SpeedDistanceDto> speedDistanceDtoList){
        List<TimeDto> outList = calculateTimeList(speedDistanceDtoList);
        int unique = new HashSet<>(speedDistanceDtoList)
                .size();
        double max = outList
                .stream()
                .max(Comparator.comparingDouble(TimeDto::getTime))
                .get()
                .getTime();
        double min = outList
                .stream()
                .min(Comparator.comparingDouble(TimeDto::getTime))
                .get()
                .getTime();
        double average = outList
                .stream()
                .mapToDouble(TimeDto::getTime)
                .average()
                .getAsDouble();
        return new TimeDtoListStat(unique, max, min, average, outList);
    }
}
