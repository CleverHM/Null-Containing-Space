package com.ssafy.pjt1.model;

import io.swagger.annotations.ApiModelProperty;


public class BasicResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    @ApiModelProperty(value = "object", position = 3)
    public Object object;
    @ApiModelProperty(value = "matchok", position = 4)
    public boolean matchok;
    @ApiModelProperty(value = "teamdate", position = 5)
    public TeamData teamdate;
}
