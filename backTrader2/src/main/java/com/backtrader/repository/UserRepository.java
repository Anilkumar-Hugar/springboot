package com.backtrader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backtrader.userentity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	public Users findByEmail(String email);
}
