package com.ssafy.pjt1.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ReplyRequest {
	@ApiModelProperty(required = true)
	@NotNull
	String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
