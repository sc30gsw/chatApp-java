package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MRoom;
import com.example.demo.entity.MUser;
import com.example.demo.entity.TRoomUser;
import com.example.demo.form.RoomForm;
import com.example.demo.service.RoomService;
import com.example.demo.service.RoomUserService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserDetailServiceImpll;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class RoomController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomUserService roomUserService;
	
	@GetMapping("/")
	public String getRoomsIndex(Model model, @AuthenticationPrincipal UserDetailServiceImpll loginUser) {
		
		//ログインユーザーのユーザー名取得
		String username = loginUser.getUser().getName();
		model.addAttribute("username", username);
				
		List<MRoom> rooms =roomService.getLoginUserRooms(loginUser);
		model.addAttribute("rooms", rooms);
		
		return "rooms/index";
	}

	@GetMapping("/rooms/new")
	public String getRoomsNew(Model model, @ModelAttribute("form") RoomForm form, @AuthenticationPrincipal UserDetailServiceImpll loginUser) {
		
		//ログインユーザーのユーザーID取得
		int currentUserId = loginUser.getUser().getId();
		
		//ユーザー取得(複数件)
		List<MUser> users = userService.getUsers(currentUserId);
		model.addAttribute("users", users);
		
		return "rooms/new";
	}
	
	@PostMapping("/rooms/new")
	public String postRoomsNew(Model model, @Validated @ModelAttribute("form") RoomForm form, BindingResult result, TRoomUser roomUser, @AuthenticationPrincipal UserDetailServiceImpll loginUser) {
		
		//入力チェック
		if(result.hasErrors()) {
			/* NG:チャットルーム作成画面に戻る*/
			return "redirect:/rooms/new";
		}
		
		log.info(form.toString());
		
		//チャットルーム・roomUserTBL登録
		roomUserService.registRoomUser(form, roomUser, loginUser);
		
		return "redirect:/";
	}
}
