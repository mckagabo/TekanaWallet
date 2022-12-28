package com.tekanawallet.tekanawallet.registration.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tekanawallet.tekanawallet.dto.UserDto;
import com.tekanawallet.tekanawallet.registration.model.Role;
import com.tekanawallet.tekanawallet.registration.model.User;
import com.tekanawallet.tekanawallet.registration.repository.RoleRepository;
import com.tekanawallet.tekanawallet.registration.repository.UserRepository;

import tekanawallet.tekanawallet.enums.ERoles;

@Service
public class UserServiceImpl implements UserService{
	 private UserRepository userRepository;
	 private RoleRepository roleRepository; 
	 private PasswordEncoder passwordEncoder;
	 
	   public UserServiceImpl(UserRepository userRepository,
               RoleRepository roleRepository,
               PasswordEncoder passwordEncoder) {
this.userRepository = userRepository;
this.roleRepository = roleRepository;
this.passwordEncoder = passwordEncoder;
}
	
	@Override
	public void saveUser(UserDto userDto) {
		User user = new User();
        user.setNames(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName(ERoles.ADMIN);
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
		
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Role checkRoleExist() {
	        Role role = new Role();
	        role.setName(ERoles.ADMIN);
	        return roleRepository.save(role);
	   }

	
}
