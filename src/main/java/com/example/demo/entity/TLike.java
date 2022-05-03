package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TLike implements Serializable {
	
	private static final long serialVersionUID = 5157962990625055876L;
	
	private int id;
	
	private int messageId;
	
	private int userId;
	
	@DateTimeFormat(pattern = "yyyy_MM_dd HH:mm:ss")
	private LocalDateTime createdAt;
}
