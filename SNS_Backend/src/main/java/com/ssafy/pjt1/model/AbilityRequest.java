package com.ssafy.pjt1.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AbilityRequest {
	@ApiModelProperty(required = true)
	@NotNull
	List<Integer> ability;

	public List<Integer> getAbility() {
		return ability;
	}

	public void setAbility(List<Integer> ability) {
		this.ability = ability;
	}
}
