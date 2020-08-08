package com.ssafy.pjt1.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	private boolean gender;
	private String gitaddr;
	private String blogaddr;
	private String intro;
	private boolean matchok;
	private int preferProject;
	private boolean leader;

	@OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
	private Set<UserFollow> followings;
	
	@OneToMany(mappedBy = "to", cascade = CascadeType.ALL)	
	private Set<UserFollow> followers;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<TagFollow> tagfollows;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Reply> replys = new HashSet<Reply>();
		
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)	
	private Set<PostLike> postlikes;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Alarm> alarms = new HashSet<Alarm>();
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ABILITY_ID")
	private Ability ability;
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROFILE_ID")
	private Profile profile;
	
	//team : 유저 (1 : N 관계)
	@ManyToOne(optional = true)
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	@CreationTimestamp
	@Column(updatable = false)
	private Date createDate;

	public User() {

	}

	public User(String nickname, String password, String email, boolean lead) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.leader = lead;
	}
	
	public User(String nickname, String password, String email, String name, String tel, int age, boolean gender, String gitaddr, String blogaddr, String intro, Ability ability, Profile profile, boolean lead, boolean matchok, int preferProject) {
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
		this.ability = ability;
		this.profile = profile;
		this.leader = lead;
		this.matchok = matchok;
		this.preferProject = preferProject;
	}
	
	
	public User(int uid, String nickname, String password, String email, String name, String tel, int age, boolean gender, boolean lead) {
		this.uid = uid;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.name = name;
		this.tel = tel;
		this.age = age;
		this.gender = gender;
		this.leader = lead;
	}
	
	public User(int uid, String nickname, String password, String email, String name, String tel, int age,
			boolean gender, String gitaddr, String blogaddr, String intro, Set<UserFollow> followings,
			Set<UserFollow> followers, Set<TagFollow> tagfollows, Set<Post> posts, Set<PostLike> postlikes,
			Date createDate) {
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
	
	public Set<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(Set<Alarm> alarms) {
		this.alarms = alarms;
	}

	public Set<PostLike> getPostlikes() {
		return postlikes;
	}

	public void setPostlikes(Set<PostLike> postlikes) {
		this.postlikes = postlikes;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getPreferProject() {
		return preferProject;
	}

	public void setPreferProject(int preferProject) {
		this.preferProject = preferProject;
	}

	public boolean getLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public boolean isMatchok() {
		return matchok;
	}

	public void setMatchok(boolean matchok) {
		this.matchok = matchok;
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		this.ability = ability;
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


