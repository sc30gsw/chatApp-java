package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessagesDetail {

	private int id;
	private String name;
	private String content;
	private int roomId;
	private int userId;
	private String image;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
