package com.searcin.utils;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenGenerator {
	
	public String getToken(String username, List<String> roles) {
		return "Bearer " + Jwts.builder().setSubject(username).claim("roles", roles).setIssuedAt(new Date())
		.signWith(SignatureAlgorithm.HS256, "ThisIsSecretKey").compact();
	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey("ThisIsSecretKey").parseClaimsJws(token).getBody();
	}
}
