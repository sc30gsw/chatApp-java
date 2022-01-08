package com.example.demo.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.demo.entity.TMessages;
import com.example.demo.form.MessageForm;
import com.example.demo.service.impl.UserDetailServiceImpll;

public interface MessageService {
	
	/**メッセージ登録*/
	public void insertMessage(TMessages message, MessageForm form, @AuthenticationPrincipal UserDetailServiceImpll loginUser, int roomId);

}
