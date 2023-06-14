package com.backtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backtrader.jwtConfig.JwtToken;
import com.backtrader.requestresponse.AuthRequest;
import com.backtrader.requestresponse.AuthResponse;
import com.backtrader.service.UserService;
import com.backtrader.userentity.Users;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/backtrader")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtToken jwtToken;
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
		AuthResponse response = null;
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String token=jwtToken.generateJwtToken(authentication);
		List<String> roles = userDetails.getAuthorities().stream().map(list -> list.getAuthority()).toList();
		response = new AuthResponse(userDetails.getUsername(), roles,token);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<Users> createUser(@RequestBody Users user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
}
