package com.ssafy.pjt1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Valid
@ToString
public class SignupRequest {
    @ApiModelProperty(required = true)
    @NotNull
    String email;
    
    @ApiModelProperty(required = true)
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    String password;
    
    @ApiModelProperty(required = true)
    @NotNull
    String nickname;
    
    @ApiModelProperty(required = true)
    @NotNull
    String name;
    
    @ApiModelProperty(required = true)
    @NotNull
    String tel;
    
    @ApiModelProperty(required = true)
    @NotNull
    int age;
    
    @ApiModelProperty(required = true)
    @NotNull
    boolean gender;
    
    @ApiModelProperty(required = true)
    private String gitaddr;
    @ApiModelProperty(required = true)
	private String blogaddr;
    @ApiModelProperty(required = true)
	private String intro;
    
    @ApiModelProperty(required = true)
    @NotNull
    List<Integer> ability;
    

    
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

	public List<Integer> getAbility() {
		return ability;
	}

	public void setAbility(List<Integer> ability) {
		this.ability = ability;
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

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
