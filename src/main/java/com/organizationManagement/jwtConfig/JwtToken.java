package com.organizationManagement.jwtConfig;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtToken {
	Logger logger = LoggerFactory.getLogger(JwtToken.class);
	long expDate = 30000;
	String key = "123345567jdhsfish";

	public String generateJwtToken(Authentication authentication) {

		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expDate))
				.signWith(SignatureAlgorithm.HS512, key.getBytes()).compact();
	}

	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Invalid JWT token: {}", e.getMessage());
			}
		} catch (ExpiredJwtException e) {
			if (logger.isErrorEnabled()) {
				logger.error("JWT token is expired: {}", e.getMessage());
			}
		} catch (UnsupportedJwtException e) {
			if (logger.isErrorEnabled()) {
				logger.error("JWT token is unsupported: {}", e.getMessage());
			}
		} catch (IllegalArgumentException e) {
			if (logger.isErrorEnabled()) {
				logger.error("JWT claims string is empty: {}", e.getMessage());
			}
		}

		return false;
	}
}
