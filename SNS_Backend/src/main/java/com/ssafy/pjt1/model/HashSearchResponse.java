package com.ssafy.pjt1.model;

import java.util.List;
import java.util.Set;

import io.swagger.annotations.ApiModelProperty;

public class HashSearchResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    @ApiModelProperty(value = "hashfeeddata", position = 3)
    public Set<FeedData> hashfeeddata;
}
