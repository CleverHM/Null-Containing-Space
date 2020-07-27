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
@Table(name = "Files")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Files {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int fid;
	
	private String filename;
	private String fileOriname;
	private String fileurl;
	
	
	@OneToOne(mappedBy = "files")
	private Post post;
	
	
	public Files() {
		
	}

	public Files(int fid, String filename, String fileOriname, String fileurl) {
		this.fid = fid;
		this.filename = filename;
		this.fileOriname = fileOriname;
		this.fileurl = fileurl;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileOriname() {
		return fileOriname;
	}

	public void setFileOriname(String fileOriname) {
		this.fileOriname = fileOriname;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	
	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}
	
}
