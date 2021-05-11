package com.meritamerica.assignment7.model.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class AuthTokenFilter {

	
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		
		return null;
	}
}
