package org.mywebservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final double time;

    public TimeDto(double time) {
        this.time = time;
    }

    public TimeDto() {
        time = 0;
    }

    public double getTime() {
        return time;
    }
}
