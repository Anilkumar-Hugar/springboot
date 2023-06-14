package com.backtrader.jwtutils;

import java.util.Date;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {
	private int expTime = 30000;
	private String key = "1234asnildj";

	public String genrateToken(String email) {
		//UserDetails details=(UserDetails)authentication.getPrincipal();
		return Jwts.builder().setSubject("SecuredToken").setIssuer(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expTime))
				.signWith(SignatureAlgorithm.HS256, key.getBytes()).compact();
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody().getIssuer();
	}

	public boolean isTokenExpired(String token) {
		return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody().getExpiration()
				.before(new Date(System.currentTimeMillis()));
	}

	public boolean validateToken(String token) {
		Date expirationTime = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody()
				.getExpiration();
		return (getUsername(token) != null && expirationTime.before(new Date(System.currentTimeMillis())));
	}
	public void getRole(String token) {
		Claims claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();
		System.out.println(claims.get("role"));
	}
}
