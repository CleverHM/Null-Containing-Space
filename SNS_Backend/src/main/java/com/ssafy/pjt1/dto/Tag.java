package com.ssafy.pjt1.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

	@ManyToMany(mappedBy = "tags")
	private Set<User> users;
	
	
	public Tag() {
	
	}
	
	public Tag(String name) {
		this.name = name;
		this.users = new HashSet<User>();
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


	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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
