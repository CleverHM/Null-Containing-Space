package com.ssafy.pjt1.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class MachingRequest {
	@ApiModelProperty(required = true)
	@NotNull
	String nickname;
	@ApiModelProperty(required = true)
	@NotNull
	List<Integer> ability;

	public MachingRequest(@NotNull String nickname, @NotNull List<Integer> ability) {
		this.nickname = nickname;
		this.ability = ability;
	}

	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<Integer> getAbility() {
		return ability;
	}

	public void setAbility(List<Integer> ability) {
		this.ability = ability;
	}
}
