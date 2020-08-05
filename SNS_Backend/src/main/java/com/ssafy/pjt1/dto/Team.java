package com.ssafy.pjt1.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Team")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int teamid;
	
	private String title;
	private String teamIntro;
	private int memberCnt;
	private int preferProject; //0 : 웹기술, 1 : 웹디자인, 2 : IOT 
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<User>();
	
	@CreationTimestamp
	@Column(updatable = false)
	private Date createDate;

	public Team() {
		
	}
	
	public Team(String title, String teamIntro, int memberCnt) {
		super();
		this.title = title;
		this.teamIntro = teamIntro;
		this.memberCnt = memberCnt;
	}

	public Team(int teamid, String title, String teamIntro, int memberCnt, int preferProject) {
		super();
		this.teamid = teamid;
		this.title = title;
		this.teamIntro = teamIntro;
		this.memberCnt = memberCnt;
		this.preferProject = preferProject;
	}

	

	public int getPreferProject() {
		return preferProject;
	}

	public void setPreferProject(int preferProject) {
		this.preferProject = preferProject;
	}

	public int getTeamid() {
		return teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTeamIntro() {
		return teamIntro;
	}

	public void setTeamIntro(String teamIntro) {
		this.teamIntro = teamIntro;
	}

	public int getMemberCnt() {
		return memberCnt;
	}

	public void setMemberCnt(int memberCnt) {
		this.memberCnt = memberCnt;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}

