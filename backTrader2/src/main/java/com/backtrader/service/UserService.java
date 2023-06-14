package com.backtrader.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backtrader.repository.UserRepository;
import com.backtrader.userentity.Users;
import com.backtrader.utils.LoginRequest;

@Service
public class UserService{
//public class UserService implements UserDetailsService {
//	//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	private UserRepository userRepository;

	public Users signUp(Users users) {
		users.setPassword((users.getPassword()));
		return userRepository.save(users);
	}

	public Users findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public String login(LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		Users users = userRepository.findByEmail(email);
		//if (users != null && encoder.matches(loginRequest.getPassword(), users.getPassword()))
			if(users!=null&&users.getPassword().equals(loginRequest.getPassword()))
			return users.getFullname() + " Logged in successfully";
		else
			return "Invalid credentials";
	}

//	public String forgotPassword(String email) {
//		Users users = userRepository.findByEmail(email);
//		// logic to be written to send email for password reset
//		return "email sent with reset password link";
//	}

	public String changePassword(String email, String password) {
		Users users = userRepository.findByEmail(email);
		if (users != null) {
			users.setPassword(password);
			userRepository.save(users);
			return "password has been changed";
		} else {
			return "User doesnot exist please login";
		}
	}

//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		return (UserDetails) userRepository.findByEmail(email);
//	}
}
