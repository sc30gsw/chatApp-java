package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MRoom {

	private int id;
	private String roomName;
	
	@DateTimeFormat(pattern = "yyyy_MM_dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	private List<TRoomUser> roomUserList;
}
