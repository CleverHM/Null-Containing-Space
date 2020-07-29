package com.ssafy.pjt1.dto;

import java.time.LocalDateTime;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "User")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int uid;

	private String nickname;
	private String password;
	private String email;
	private String name;
	private String tel;
	private int age;
	private boolean gender; // 1이면 남자, 2이면 여자

// 유저 : 유저 (N : N 관계 ) -> 유저가 팔로우 하는 유저들.
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Userfollow", joinColumns = {
			@JoinColumn(name = "From_id", referencedColumnName = "uid") }, inverseJoinColumns = {
					@JoinColumn(name = "To_id", referencedColumnName = "uid") })
	@JsonIgnore
	private Set<User> followings = new HashSet<User>();

	@ManyToMany(mappedBy = "followings")
	@JsonIgnore
	private Set<User> followers = new HashSet<User>();

// 유저  : 게시물 (N : N 관계) -> 유저가 팔로우 하는 태그들.
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Tagfollow", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "tag_id") })
	private Set<Tag> tags = new HashSet<Tag>();

// 유저  : 게시물 (1 : N 관계)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();

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

	public User(String nickname, String password, String email, String name, String tel, int age, boolean gender) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.name = name;
		this.tel = tel;
		this.age = age;
		this.gender = gender;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
