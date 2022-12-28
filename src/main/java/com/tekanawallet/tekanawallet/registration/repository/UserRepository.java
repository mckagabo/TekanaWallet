package com.tekanawallet.tekanawallet.registration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekanawallet.tekanawallet.registration.model.User;

public interface UserRepository extends JpaRepository<User,UUID>{
	 User findByEmail(String email);
}
