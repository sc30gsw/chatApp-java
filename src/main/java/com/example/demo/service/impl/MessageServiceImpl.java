package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.TMessages;
import com.example.demo.form.MessageForm;
import com.example.demo.repository.MessageMapper;
import com.example.demo.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageMapper mapper;

	@Transactional
	@Override
	public void insertMessage(TMessages message, MessageForm form,
			@AuthenticationPrincipal UserDetailServiceImpll loginUser, int roomId) {
		//フォームから入力値取得
		message.setContent(form.getContent());
		
		//チャットルームのID設定
		message.setRoomId(roomId);

		//ログインユーザーのID取得
		int userId = loginUser.getUser().getId();

		//ログインユーザーID設定
		message.setUserId(userId);

		//現在時刻の取得
		LocalDateTime now = LocalDateTime.now();
		message.setCreatedAt(now);
		message.setUpdatedAt(now);
		
		//メッセージ登録
		mapper.insertOneMessage(message);
	}
}
