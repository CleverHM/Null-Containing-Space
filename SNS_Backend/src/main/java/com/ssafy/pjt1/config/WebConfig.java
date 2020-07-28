package com.ssafy.pjt1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.pjt1.component.JwtInterceptor;

public class WebConfig implements WebMvcConfigurer {
	private static final String[] EXCLUDE_PATHS = {
	}; //토큰인증을 제외할 path 입력.
	
	@Autowired
	private JwtInterceptor jwtinterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtinterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(EXCLUDE_PATHS);
	}
}
