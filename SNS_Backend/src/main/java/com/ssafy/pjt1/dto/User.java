// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.ssafy.pjt1.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    private String nickname;
    private String password;
    private int follow;
    private int following;
    private String blog_addr;
    private String git_addr;
    private String introduce;
    private String find_q;
    private String find_a;
    
    
    @OneToMany
    @JoinColumn(name = "id")
    private List<Follow> Follows = new ArrayList<>(); 
    
    @OneToMany
    @JoinColumn(name = "id")
    private List<Following> Followings = new ArrayList<>(); 
    

    @JsonIgnore
    private String email;
    

   
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDate;
    
    public User() {
    	
    }

	public User(String nickname, String password, String email) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFollow() {
		return follow;
	}

	public void setFollow(int follow) {
		this.follow = follow;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public String getBlog_addr() {
		return blog_addr;
	}

	public void setBlog_addr(String blog_addr) {
		this.blog_addr = blog_addr;
	}

	public String getGit_addr() {
		return git_addr;
	}

	public void setGit_addr(String git_addr) {
		this.git_addr = git_addr;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getFind_q() {
		return find_q;
	}

	public void setFind_q(String find_q) {
		this.find_q = find_q;
	}

	public String getFind_a() {
		return find_a;
	}

	public void setFind_a(String find_a) {
		this.find_a = find_a;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
