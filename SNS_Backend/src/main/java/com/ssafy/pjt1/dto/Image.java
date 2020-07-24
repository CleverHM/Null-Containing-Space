package com.ssafy.pjt1.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Image")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int iid;
	
	private String name;
	private String uploadname;
	private String imageurl;
	
	
	public Image() {
		
	}

	public Image(String name, String uploadname, String imageurl) {
		this.name = name;
		this.uploadname = uploadname;
		this.imageurl = imageurl;
	}

	public int getIid() {
		return iid;
	}
	
	public void setIid(int iid) {
		this.iid = iid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUploadname() {
		return uploadname;
	}
	
	public void setUploadname(String uploadname) {
		this.uploadname = uploadname;
	}
	
	public String getImageurl() {
		return imageurl;
	}
	
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
}
