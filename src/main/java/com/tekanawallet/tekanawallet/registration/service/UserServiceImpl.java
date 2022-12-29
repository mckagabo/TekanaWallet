package com.tekanawallet.tekanawallet.registration.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tekanawallet.tekanawallet.dto.UserDto;
import com.tekanawallet.tekanawallet.registration.model.Balance;
import com.tekanawallet.tekanawallet.registration.model.Role;
import com.tekanawallet.tekanawallet.registration.model.User;
import com.tekanawallet.tekanawallet.registration.repository.BalanceRepository;
import com.tekanawallet.tekanawallet.registration.repository.RoleRepository;
import com.tekanawallet.tekanawallet.registration.repository.UserRepository;

import tekanawallet.tekanawallet.enums.EGender;
import tekanawallet.tekanawallet.enums.ERoles;

@Service
public class UserServiceImpl implements UserService{
	 private UserRepository userRepository;
	 private RoleRepository roleRepository; 
	 @Autowired
	 private BalanceRepository balanceRepository;
	 private PasswordEncoder passwordEncoder;
	 
	 
	   public UserServiceImpl(UserRepository userRepository,
               RoleRepository roleRepository,
               PasswordEncoder passwordEncoder) {
this.userRepository = userRepository;
this.roleRepository = roleRepository;
this.passwordEncoder = passwordEncoder;
}
	
	@Override
	public User saveUser(UserDto userDto,ERoles userRole) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		 User registeredUser=null;
		try {
			 LocalDate dob = LocalDate.parse(userDto.getDob(), formatter);
				long startingBalance= 0;
				User user = new User();
		        user.setNames(userDto.getFirstName() + " " + userDto.getLastName());
		        user.setEmail(userDto.getEmail());
		        user.setGender(showGender(userDto.getGender()));
		        user.setAccountStatus(false);
		        user.setDob(dob);
		        user.setUserAccount(userDto.getUserName());
		        user.setRegistrationTime(LocalDateTime.now());
		        //encrypt the password once we integrate spring security   
		        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		        Role role = roleRepository.findByName(userRole);
		        if(role == null){
		            role = registerNewRole(userRole);
		        }
		        user.setRoles(Arrays.asList(role));       
		        registeredUser=userRepository.save(user);
		        user.setBalance(saveBalance(registeredUser,startingBalance));
		        return user;
		} catch (Exception e) {
			e.getMessage();
		}
		return registeredUser;
	}
 
	public EGender showGender(String gender) {
		EGender sex=null;
		if(gender.equalsIgnoreCase("male")) {
			sex=EGender.MALE;
		}else if(gender.equalsIgnoreCase("female")) {
			sex=EGender.FEMALE;
		}
		return sex;
	}
	
	@Override
	public User findByEmail(String email) {
	  User user=userRepository.findByEmail(email);
	  if(user!=null) {
		  return user;
	  }
	  return null;
	}

	@Override
	public List<UserDto> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Role registerNewRole(ERoles newRole) {
	        Role role = new Role();
	        role.setName(newRole);
	        return roleRepository.save(role);
	   }

	@Override
	public Balance saveBalance(User user,long balance) {
	   Balance balanciaga= new Balance();
	   balanciaga.setUser(user);
	   balanciaga.setBalance(balance);
	   balanciaga.setUpdatedAt(LocalDateTime.now());
	   return balanceRepository.save(balanciaga);
	}

	
}
