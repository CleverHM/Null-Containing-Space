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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private int preferProject; // 1 : 웹기술, 2 : 웹디자인, 3 : IOT

	// 백엔드
	private boolean back_cpp;
	private boolean back_java;
	private boolean back_python;
	private boolean back_php;

	// 프론트엔드
	private boolean front_html;
	private boolean front_css;
	private boolean front_javascript;

	// 데이터베이스
	private boolean db_sql;
	private boolean db_nosql;

	// 프레임워크
	private boolean frame_spring;
	private boolean frame_django;
	private boolean frame_bootstrap;
	private boolean frame_vue;
	private boolean frame_react;

	// 알고리즘
	private boolean algo;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<User>();

	// 팀 : 채팅방 (1 : 1 관계)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROOM_ID")
	private Chatroom chatroom;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Date createDate;

	public Team() {

	}

	public Team(String title, String teamIntro, int memberCnt) {
		this.title = title;
		this.teamIntro = teamIntro;
		this.memberCnt = memberCnt;
	}
	
	public Team(String title, String teamIntro, int memberCnt, int preferProject, boolean back_cpp, boolean back_java,
			boolean back_python, boolean back_php, boolean front_html, boolean front_css, boolean front_javascript,
			boolean db_sql, boolean db_nosql, boolean frame_spring, boolean frame_django, boolean frame_bootstrap,
			boolean frame_vue, boolean frame_react, boolean algo) {
		this.title = title;
		this.teamIntro = teamIntro;
		this.memberCnt = memberCnt;
		this.preferProject = preferProject;
		this.back_cpp = back_cpp;
		this.back_java = back_java;
		this.back_python = back_python;
		this.back_php = back_php;
		this.front_html = front_html;
		this.front_css = front_css;
		this.front_javascript = front_javascript;
		this.db_sql = db_sql;
		this.db_nosql = db_nosql;
		this.frame_spring = frame_spring;
		this.frame_django = frame_django;
		this.frame_bootstrap = frame_bootstrap;
		this.frame_vue = frame_vue;
		this.frame_react = frame_react;
		this.algo = algo;
	}
	
	public Chatroom getChatroom() {
		return chatroom;
	}

	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}

	public boolean isBack_cpp() {
		return back_cpp;
	}

	public void setBack_cpp(boolean back_cpp) {
		this.back_cpp = back_cpp;
	}

	public boolean isBack_java() {
		return back_java;
	}

	public void setBack_java(boolean back_java) {
		this.back_java = back_java;
	}

	public boolean isBack_python() {
		return back_python;
	}

	public void setBack_python(boolean back_python) {
		this.back_python = back_python;
	}

	public boolean isBack_php() {
		return back_php;
	}

	public void setBack_php(boolean back_php) {
		this.back_php = back_php;
	}

	public boolean isFront_html() {
		return front_html;
	}

	public void setFront_html(boolean front_html) {
		this.front_html = front_html;
	}

	public boolean isFront_css() {
		return front_css;
	}

	public void setFront_css(boolean front_css) {
		this.front_css = front_css;
	}

	public boolean isFront_javascript() {
		return front_javascript;
	}

	public void setFront_javascript(boolean front_javascript) {
		this.front_javascript = front_javascript;
	}

	public boolean isDb_sql() {
		return db_sql;
	}

	public void setDb_sql(boolean db_sql) {
		this.db_sql = db_sql;
	}

	public boolean isDb_nosql() {
		return db_nosql;
	}

	public void setDb_nosql(boolean db_nosql) {
		this.db_nosql = db_nosql;
	}

	public boolean isFrame_spring() {
		return frame_spring;
	}

	public void setFrame_spring(boolean frame_spring) {
		this.frame_spring = frame_spring;
	}

	public boolean isFrame_django() {
		return frame_django;
	}

	public void setFrame_django(boolean frame_django) {
		this.frame_django = frame_django;
	}

	public boolean isFrame_bootstrap() {
		return frame_bootstrap;
	}

	public void setFrame_bootstrap(boolean frame_bootstrap) {
		this.frame_bootstrap = frame_bootstrap;
	}

	public boolean isFrame_vue() {
		return frame_vue;
	}

	public void setFrame_vue(boolean frame_vue) {
		this.frame_vue = frame_vue;
	}

	public boolean isFrame_react() {
		return frame_react;
	}

	public void setFrame_react(boolean frame_react) {
		this.frame_react = frame_react;
	}

	public boolean getAlgo() {
		return algo;
	}

	public void setAlgo(boolean algo) {
		this.algo = algo;
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
