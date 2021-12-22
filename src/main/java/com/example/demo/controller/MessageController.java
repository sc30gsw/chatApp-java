package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MRoom;
import com.example.demo.service.RoomService;
import com.example.demo.service.impl.UserDetailServiceImpll;

@Controller
@RequestMapping("/")
public class MessageController {
	
	@Autowired
	private RoomService roomService;

	@GetMapping("/messages")
	public String getIndex(Model model, @AuthenticationPrincipal UserDetailServiceImpll loginUser) {
		
		//ログインユーザーのユーザー名取得
		String username = loginUser.getUser().getName();
		model.addAttribute("username", username);
		
		List<MRoom> rooms =roomService.getLoginUserRooms(loginUser);
		model.addAttribute("rooms", rooms);
		
		return "messages/index";
	}
	
}
