package com.searcin.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import com.searcin.utils.TokenGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

public class JWTFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		TokenGenerator jwtParser = new TokenGenerator();
		HttpServletRequest request = (HttpServletRequest) req;
		String authToken = request.getHeader("Authorization");
		
		if (authToken == null || !authToken.startsWith("Bearer ")) {
			((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header.");
		}
		else {
			try {
				String token = authToken.substring(7);
				Claims claims = jwtParser.getClaims(token);
				request.setAttribute("claims", claims);
				SecurityContextHolder.getContext().setAuthentication(getAuthentication(claims));
				filterChain.doFilter(req, res);
			} catch (SignatureException e) {
				((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
			}
		}
	}
	
	public Authentication getAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<SimpleGrantedAuthority> authorities = ((List<String>) claims.get("roles")).stream()
														.map(item -> new SimpleGrantedAuthority(item))
														.collect(Collectors.toList());		
		User principal = new User(claims.getSubject(), "", authorities);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				principal, "", authorities);
		return usernamePasswordAuthenticationToken;
	}

}
