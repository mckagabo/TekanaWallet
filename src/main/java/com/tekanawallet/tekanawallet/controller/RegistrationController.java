package com.tekanawallet.tekanawallet.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
	
	@RequestMapping({"/hi"})
	public String bite() {
		return "Twagiye muri Canada kabaye";
	}

}
