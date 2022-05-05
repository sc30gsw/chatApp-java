package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.demo.entity.TLike;
import com.example.demo.service.impl.UserDetailServiceImpll;

public interface LikeService {
	
	/**likes登録*/
	public void registLike(TLike like, int messageId, @AuthenticationPrincipal UserDetailServiceImpll loginUser);

	public List<TLike> alreadyLiked(@AuthenticationPrincipal UserDetailServiceImpll loginUser);
	
	public void destroyLike(int userId, int messageId);
}
