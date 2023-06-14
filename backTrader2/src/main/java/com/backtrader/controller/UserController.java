package com.backtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backtrader.jwtutils.JwtTokenGenerator;
import com.backtrader.service.UserService;
import com.backtrader.userentity.Users;
import com.backtrader.utils.LoginRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/backtrader")
public class UserController {
	@Autowired
	private UserService userService;
//	@Autowired
	//private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenGenerator jwtToken;

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
		String token = null;
		
		System.out.println("Token before auth "+token);
		Users users=userService.findByEmail(loginRequest.getEmail());
		token=jwtToken.genrateToken(loginRequest.getEmail());
		jwtToken.getRole(token);
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		System.out.println(userDetails);
//		token = jwtToken.genrateToken(authentication);
//		System.out.println(token);
//		List<String> roles = userDetails.getAuthorities().stream().map(list -> list.getAuthority()).toList();
		return ResponseEntity.ok(token);

	}

	@PostMapping("/create")
	public ResponseEntity<Users> createUser(@RequestBody Users user) {
		return ResponseEntity.ok(userService.signUp(user));
	}
}
