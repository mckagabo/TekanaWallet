package com.tekanawallet.tekanawallet.dto;

import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public class UserDto {
	
	    private UUID id;
	   @NotEmpty
	    private String firstName;
	    @NotEmpty
	    private String lastName;
	    @NotEmpty(message = "Email should not be empty")
	    @Email
	    private String email;
	    @NotEmpty(message = "Password should not be empty")
	    private String password;
	    
	    @NotEmpty
	    private String userName;
	    
	    @DateTimeFormat(pattern="dd/MMM/yyyy") 
	    private String dob;
	    
	    @NotEmpty
	    private String gender;
	    
		public UUID getId() {
			return id;
		}
		public void setId(UUID id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userAccount) {
			this.userName = userAccount;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
     
	    
}
