package com.tekanawallet.tekanawallet.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekanawallet.tekanawallet.config.JwtTokenUtil;
import com.tekanawallet.tekanawallet.dto.JwtResponse;
import com.tekanawallet.tekanawallet.dto.LoginDto;
import com.tekanawallet.tekanawallet.dto.UserDto;
import com.tekanawallet.tekanawallet.dto.UserResponseInfo;
import com.tekanawallet.tekanawallet.registration.model.User;
import com.tekanawallet.tekanawallet.registration.repository.RoleRepository;
import com.tekanawallet.tekanawallet.registration.repository.UserRepository;
import com.tekanawallet.tekanawallet.registration.service.UserDetailsImpl;
import com.tekanawallet.tekanawallet.registration.service.UserService;

import jakarta.validation.Valid;
import tekanawallet.tekanawallet.enums.ERoles;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	 @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;
	  
	  @Autowired 
	  UserService userService;
	  
	  @Autowired
	  UserDetailsImpl userDetailsService;

	  @Autowired
	  RoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtTokenUtil jwtUtils;
	  
	  @PostMapping("/signin")
	  public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginDto loginRequest) throws Exception{
		   authenticate(loginRequest.getUsername(), loginRequest.getPassword());	
		   final UserDetails userDetails = userDetailsService
					.loadUserByUsername(loginRequest.getUsername());
		    if(userDetails!=null) {
		   final String token  = jwtUtils.generateToken(userDetails);	
		   return ResponseEntity.ok(new JwtResponse(token));
		    }
		  
		 return ResponseEntity.status(400).body("Invalid credentials"); 
		     
	  }
	  
	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto){
		   
		  try {
			  if (userRepository.existsByEmail(userDto.getEmail())) {
			      return ResponseEntity.badRequest().body("Error: Email already exist!");
			    }
			  if(userRepository.existsByUserAccount(userDto.getUserName())) {
				 return ResponseEntity.badRequest().body("ERRROR: Username taken");
			  }
			  User user=userService.saveUser(userDto,ERoles.CUSTOMER);
			  return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Sorry"+e.getMessage());
		}
	  }
	  
	  private void authenticate(String username, String password) throws Exception {
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			} catch (DisabledException e) {
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				throw new Exception("INVALID_CREDENTIALS", e);
			}
		}
	  
	  
}
