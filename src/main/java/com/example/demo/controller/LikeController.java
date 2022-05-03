package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.TLike;
import com.example.demo.service.LikeService;
import com.example.demo.service.impl.UserDetailServiceImpll;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LikeController {
	
	@Autowired
	LikeService service;

	@PostMapping("/rooms/{roomId}/{messageId}")
	public String postLike(TLike like, @AuthenticationPrincipal UserDetailServiceImpll loginUser,
			@PathVariable("roomId") int roomId, @PathVariable("messageId") int messageId) {
		service.registLike(like, messageId, loginUser);
		
		return "redirect:/rooms/{roomId}";
	}
	
	@PostMapping("/rooms/{roomId}/{messageId}/{userId}/delete")
	public String deleteLike(@PathVariable("roomId") int roomId, @PathVariable("userId") int userId,@PathVariable("messageId") int messageId) {
		
		service.destroyLike(userId, messageId);
		
		return "redirect:/rooms/{roomId}";
	}
}
