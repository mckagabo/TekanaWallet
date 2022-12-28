package com.tekanawallet.tekanawallet.registration.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.tekanawallet.tekanawallet.dto.UserDto;
import com.tekanawallet.tekanawallet.registration.model.User;

public interface UserService{
	  
	   void saveUser(UserDto userDto);

	    User findByEmail(String email);

	    List<UserDto> findAllUsers();
	    
	   

}
