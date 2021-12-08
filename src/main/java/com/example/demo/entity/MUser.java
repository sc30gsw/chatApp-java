package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MUser {

	private int userId;
	private String email;
	private String password;
	private String passwordConfirmation;
	private String username;
	private String role;
	
	@DateTimeFormat(pattern = "yyyy_MM_dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	@DateTimeFormat(pattern = "yyyy_MM_dd HH:mm:ss")
	private LocalDateTime updatedAt;
	
}
