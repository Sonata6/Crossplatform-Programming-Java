package org.mywebservice.entity;

import java.util.Objects;

public class SpeedDistanceDto {

    private int speed;
    private int distance;

    public SpeedDistanceDto() {
        this.speed = 0;
        this.distance = 0;
    }

    public SpeedDistanceDto(int speed, int distance) {
        this.speed = speed;
        this.distance = distance;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpeedDistanceDto that = (SpeedDistanceDto) o;
        return speed == that.speed && distance == that.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, distance);
    }

    @Override
    public String toString() {
        return "SpeedDistanceDto{" +
                "speed=" + speed +
                ", distance=" + distance +
                '}';
    }
}
