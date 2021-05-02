package org.mywebservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "StatDTO")
public class TimeDtoListStat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final int unique;
    private final double maxValue;
    private final double minValue;
    private final double average;

    public int getUnique() {
        return unique;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getAverage() {
        return average;
    }

    public TimeDtoListStat() {
        unique = 1;
        maxValue = 1;
        minValue = 1;
        average = 1;
    }

    public void setTimeDtoList(List<TimeDto> timeDtoList) {
        this.timeDtoList = timeDtoList;
    }

    public List<TimeDto> getTimeDtoList() {
        return timeDtoList;
    }
    @OneToMany
    @JoinTable(
            name = "TimeDtoStat",
            joinColumns = @JoinColumn(name = "TimeDto_id")
    )
    private List<TimeDto> timeDtoList;

    public TimeDtoListStat(int unique, double maxValue, double minValue, double average, List<TimeDto> timeDtoList) {
        this.unique = unique;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.average = average;
        this.timeDtoList = timeDtoList;
    }
}
