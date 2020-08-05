package com.ssafy.pjt1.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auth {
	
	@Id
    private String email;
	
    private String number;
    
    public Auth() {
    	
    }
    
	public Auth(String email, String msg) {
		this.email = email;
		this.number = msg;
	}
	public String getAuth_email() {
		return email;
	}
	public void setAuth_email(String auth_email) {
		this.email = auth_email;
	}
	public String getAuth_number() {
		return number;
	}
	public void setAuth_number(String auth_number) {
		this.number = auth_number;
	}
}
