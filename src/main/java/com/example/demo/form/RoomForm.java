package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RoomForm {

	@NotBlank
	private String roomName;
	
	private int userId;
}
