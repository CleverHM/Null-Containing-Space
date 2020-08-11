package com.ssafy.pjt1.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class AlarmResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    @ApiModelProperty(value = "teamalarm", position = 3)
    public List<MyAlarm> teamalarm;
    @ApiModelProperty(value = "snsalarm", position = 4)
    public List<MyAlarm> snsalarm;
}
