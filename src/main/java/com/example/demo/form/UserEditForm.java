package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.example.demo.validate.Unused;

import lombok.Data;

@Data
public class UserEditForm {

	@NotBlank
	@Length(min = 4, max = 30)
	private String name;

	@NotBlank
	@Email
	@Unused
	private String email;
}
