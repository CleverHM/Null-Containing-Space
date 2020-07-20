package com.ssafy.pjt1.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class LoginRequest {
	 @ApiModelProperty(required = true)
	    @NotNull
	    String email;
	    @ApiModelProperty(required = true)
	    @NotNull
	    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
	    String password;

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
}
