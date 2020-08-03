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
	private String gitaddr;
	private String blogaddr;
	private String intro;

	@OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
	private Set<UserFollow> followings;
	
	@OneToMany(mappedBy = "to", cascade = CascadeType.ALL)	
	private Set<UserFollow> followers;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<TagFollow> tagfollows;

	// 유저  : 게시물 (1 : N 단방향 관계)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();
	
	// 유저  : 댓글 (1 : N 단방향 관계)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Reply> replys = new HashSet<Reply>();
	
	//유저 : 좋아요 (1 : N 관계)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)	
	private Set<PostLike> postlikes;

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
	
	public User(int uid, String nickname, String password, String email, String name, String tel, int age,
			boolean gender, String gitaddr, String blogaddr, String intro, Set<UserFollow> followings,
			Set<UserFollow> followers, Set<TagFollow> tagfollows, Set<Post> posts, Set<PostLike> postlikes,
			LocalDateTime createDate) {
		this.uid = uid;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.name = name;
		this.tel = tel;
		this.age = age;
		this.gender = gender;
		this.gitaddr = gitaddr;
		this.blogaddr = blogaddr;
		this.intro = intro;
		this.followings = followings;
		this.followers = followers;
		this.tagfollows = tagfollows;
		this.posts = posts;
		this.postlikes = postlikes;
		this.createDate = createDate;
	}

	
	
	public String getGitaddr() {
		return gitaddr;
	}

	public void setGitaddr(String gitaddr) {
		this.gitaddr = gitaddr;
	}

	public String getBlogaddr() {
		return blogaddr;
	}

	public void setBlogaddr(String blogaddr) {
		this.blogaddr = blogaddr;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Set<Reply> getReplys() {
		return replys;
	}

	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
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

	public Set<UserFollow> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UserFollow> followers) {
		this.followers = followers;
	}

	public Set<UserFollow> getFollowings() {
		return followings;
	}

	public void setFollowings(Set<UserFollow> followings) {
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

	public Set<TagFollow> getTagfollows() {
		return tagfollows;
	}

	public void setTagfollows(Set<TagFollow> tagfollows) {
		this.tagfollows = tagfollows;
	}
}
