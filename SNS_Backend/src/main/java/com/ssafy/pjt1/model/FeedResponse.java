package com.ssafy.pjt1.model;

import java.util.List;
import java.util.Set;

import io.swagger.annotations.ApiModelProperty;

public class FeedResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    @ApiModelProperty(value = "feeddata", position = 5)
    public List<FeedData> feeddata;
    @ApiModelProperty(value = "hashfeeddata", position = 6)
    public Set<FeedData> hashfeeddata;
}
