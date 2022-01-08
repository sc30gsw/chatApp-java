package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MessageForm {

	@NotBlank
	private String content;
	
	//画像入力時
	private MultipartFile multiPartFile;
}
