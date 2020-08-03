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
@Table(name = "Profile")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int pfid;
	
	private String filename;
	private String fileOriname;
	private String fileurl;
	
	//프로필 사진 : 유저(1 : 1관계)
	@OneToOne(mappedBy = "profile")
	private User user;

	public Profile() {}
	
	public Profile(int pfid, String filename, String fileOriname, String fileurl, User user) {
		this.pfid = pfid;
		this.filename = filename;
		this.fileOriname = fileOriname;
		this.fileurl = fileurl;
		this.user = user;
	}
	
	public int getPfid() {
		return pfid;
	}

	public void setPfid(int pfid) {
		this.pfid = pfid;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
