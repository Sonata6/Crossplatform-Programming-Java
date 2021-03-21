package org.MyWebService;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDto {

    @JsonProperty("num")
    private double time;

    public ResultDto(double time){
        this.time = time;
    }
}
