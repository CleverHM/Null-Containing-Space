package com.ssafy.pjt1.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.pjt1.dto.Image;
import com.ssafy.pjt1.dto.Tag;

import io.swagger.annotations.ApiModelProperty;

public class PostRequest {
	@ApiModelProperty(required = true)
	@NotNull
	String title;
	
	@ApiModelProperty(required = true)
	@NotNull
	String Content;
	
	@ApiModelProperty(required = true)
	@NotNull
	Image img;
	
	@ApiModelProperty(required = true)
	@NotNull
	@JsonIgnore
	Set<Tag> tags = new HashSet<Tag>();


	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}

	
	public Image getImg() {
		return img;
	}

	
	public void setImg(Image img) {
		this.img = img;
	}

	
	public Set<Tag> getTags() {
		return tags;
	}

	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
