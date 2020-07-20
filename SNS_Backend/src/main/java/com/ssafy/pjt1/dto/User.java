// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.ssafy.pjt1.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private int uid;

    private String nickname;
    private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public LocalDateTime getCreateDate() {
//		return createDate;
//	}
//
//	public void setCreateDate(LocalDateTime createDate) {
//		this.createDate = createDate;
//	}
}
//