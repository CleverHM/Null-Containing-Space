package com.ssafy.pjt1.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@Table(name = "Tag")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int tid;
	
	private String name;
	
	@OneToMany(mappedBy = "tag")
	private Set<TagFollow> tagfollows;
	
	@OneToMany(mappedBy = "tag")
	private Set<PostTag> posttags;
	
	public Tag() {
	
	}
	
	public Tag(String name) {
		this.name = name;
		this.tagfollows = new HashSet<TagFollow>();
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TagFollow> getTagfollows() {
		return tagfollows;
	}

	public void setTagfollows(Set<TagFollow> tagfollows) {
		this.tagfollows = tagfollows;
	}
	
	
	public Set<PostTag> getPosttags() {
		return posttags;
	}

	
	public void setPosttags(Set<PostTag> posttags) {
		this.posttags = posttags;
	}

	@Override
	public boolean equals(Object obj) {
		Tag t = (Tag) obj;
		if (t.getName() == this.name) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tid);
	}
	
}
