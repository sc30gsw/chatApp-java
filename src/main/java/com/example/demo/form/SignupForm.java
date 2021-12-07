package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Length(min = 6, max = 128)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;
	
	@NotBlank
	@Length(min = 6, max = 128)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String passwordConfirmation;
	
	@NotBlank
	@Length(min = 4, max = 30)
	private String username;
}
