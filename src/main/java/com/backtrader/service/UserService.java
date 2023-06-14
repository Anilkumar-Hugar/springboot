package com.backtrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backtrader.repository.UserRepository;
import com.backtrader.userentity.Users;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	public Users createUser(Users user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(username);
		if(user!=null) {
			return new org.springframework.security.core.userdetails.User(user.getFullname(), user.getPassword(),user.getRoles());

		}
		else {
			throw new UsernameNotFoundException("Unable to fetch user details");
		}
		}
}
