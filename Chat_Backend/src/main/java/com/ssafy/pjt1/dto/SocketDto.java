package com.ssafy.pjt1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// Data 어노테이션을 사용하여 Getter, Setter 자동 생성.
@Data

// 생성자를 자동 생성.
@AllArgsConstructor
public class SocketDto {
	private String nickname;
	private String content;
}
