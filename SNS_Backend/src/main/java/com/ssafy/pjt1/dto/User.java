package com.ssafy.pjt1.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int uid;

	private String nickname;
	private String password;
	private String email;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Follow", joinColumns = {
			@JoinColumn(name = "From_id", referencedColumnName = "uid") }, inverseJoinColumns = {
					@JoinColumn(name = "To_id", referencedColumnName = "uid") })
	private Set<User> followings;

	@ManyToMany(mappedBy = "followings")
	private Set<User> followers;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createDate;

	public User() {

	}
	public User(String nickname, String password, String email) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.followers = new HashSet<User>();
		this.followings = new HashSet<User>();
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
	public void setFollow(int follow) {
		this.follow = follow;
	}
	public int getUid() {
		
		return uid;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public int getUid() {
>>>>>>> f154c9fbdc65530ac7fc7b4ecd18d819ccd305cc

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
	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public Set<User> getFollowing() {
		return followings;
	}

	public void setFollowing(Set<User> followings) {
		this.followings = followings;
	}
}
