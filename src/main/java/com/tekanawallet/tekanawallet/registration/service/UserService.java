package com.tekanawallet.tekanawallet.registration.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.tekanawallet.tekanawallet.dto.UserDto;
import com.tekanawallet.tekanawallet.registration.model.Balance;
import com.tekanawallet.tekanawallet.registration.model.User;

import tekanawallet.tekanawallet.enums.ERoles;

public interface UserService{
	  
	   

	    User findByEmail(String email);

	    List<UserDto> findAllUsers();

		User saveUser(UserDto userDto, ERoles userRole);
	    
	    Balance saveBalance(User user,long balance);

}
