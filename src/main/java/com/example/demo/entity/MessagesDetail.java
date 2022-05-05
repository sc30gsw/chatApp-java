package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MessagesDetail {

	private int id;
	private String name;
	private String content;
	private int roomId;
	private int userId;
	private String image;

	@DateTimeFormat(pattern = "yyyy_MM_dd HH:mm:ss")
	private LocalDateTime createdAt;

	@DateTimeFormat(pattern = "yyyy_MM_dd HH:mm:ss")
	private LocalDateTime updatedAt;

	private List<TLike> likes;

	public List<TLike> likeCounts() {
		return likes.stream().filter(like -> like.getMessageId() == id).collect(Collectors.toList());
	}
	
}
