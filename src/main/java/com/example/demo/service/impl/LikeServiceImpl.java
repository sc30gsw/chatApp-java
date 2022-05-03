package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.TLike;
import com.example.demo.repository.LikeMapper;
import com.example.demo.service.LikeService;

import lombok.val;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

	@Autowired
	LikeMapper mapper;
	
	@Override
	public void registLike(TLike like, int messageId, @AuthenticationPrincipal UserDetailServiceImpll loginUser) {

		// ユーザーID取得
		val userId = loginUser.getUser().getId();
		
		like.setMessageId(messageId);
		like.setUserId(userId);
		
		// 現在時刻の取得
		LocalDateTime now = LocalDateTime.now();
		like.setCreatedAt(now);

		// いいね登録
		mapper.insertLike(like);
	}
	
	@Override
	public List<TLike> alreadyLiked(@AuthenticationPrincipal UserDetailServiceImpll loginUser) {
		// ユーザーID取得
		val userId = loginUser.getUser().getId();
		
		return mapper.likeCorrespondingMessage(userId);
	}
	
	@Override
	public int messageLikesCount(int messageId) {
		return mapper.likesCount(messageId);
	}
	
	@Override
	public void destroyLike(int userId, int messageId) {
		mapper.deleteLikeOne(userId, messageId);
	}
}
