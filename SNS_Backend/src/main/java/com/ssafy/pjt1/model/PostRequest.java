package com.ssafy.pjt1.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	String img;
	
	@ApiModelProperty(required = true)
	@NotNull
	@JsonIgnore
	Set<Tag> tags = new HashSet<Tag>();

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		Content = content;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the tags
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	
}
