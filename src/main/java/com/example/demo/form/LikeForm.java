package com.example.demo.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class LikeForm implements Serializable {

	private static final long serialVersionUID = 2516444417639710527L;
	
	private int messageId;
}
