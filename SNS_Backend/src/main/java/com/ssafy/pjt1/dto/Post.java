package com.ssafy.pjt1.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Post")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int pid;
	private String title;
	private String content;
	private int viewCount;
	
	//게시물 : 유저 (N : 1 관계)
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	//게시물 : 이미지(1 : 1 관계)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FILES_ID")
	private Files files;
	
	//게시물 : 태그(1 : N 관계)
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<PostTag> posttags;
	
	//게시물 : 좋아요 (1 : N 관계)
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)	
	private Set<PostLike> postlikes;
	
	//게시물 : 댓글 (1 : N 관계)
	@JsonIgnore
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private List<Reply> replies;

	@CreationTimestamp
	@Column(updatable = false)
	private Date createDate;
	
	public Post() {

	}
	 
	public Post(int pid, String title, String content, Set<PostTag> posttags, User user, Files files) {
		this.pid = pid;
		this.title = title;
		this.content = content;
		this.posttags = posttags;
		this.user = user;
		this.files = files;
		this.viewCount = 0;
	}


	public Post(String title, String content, Files files) {
		this.title = title;
		this.content = content;
		this.files = files;
	}
	
	
	
	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public Set<PostLike> getPostlikes() {
		return postlikes;
	}

	public void setPostlikes(Set<PostLike> postlikes) {
		this.postlikes = postlikes;
	}

	public Set<PostTag> getPosttags() {
		return posttags;
	}

	public void setPosttags(Set<PostTag> posttags) {
		this.posttags = posttags;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
