package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MRoom;
import com.example.demo.entity.TMessages;
import com.example.demo.form.MessageForm;
import com.example.demo.service.MessageService;
import com.example.demo.service.RoomService;
import com.example.demo.service.impl.UserDetailServiceImpll;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class MessageController {

	@Autowired
	private MessageService service;

	@Autowired
	private RoomService roomService;

	@PostMapping("/rooms/{roomId}/message")
	public String postMessage(Model model, TMessages message, @Validated @ModelAttribute("form") MessageForm form,
			BindingResult result, @AuthenticationPrincipal UserDetailServiceImpll loginUser,
			@PathVariable("roomId") int roomId) throws IOException {

		//チャットルーム1件取得
		MRoom room = roomService.getRoomOne(roomId);
		
		if (result.hasErrors()) {
			//NG:メッセージ送信画面にリダイレクト
			return "redirect:/rooms/{roomId}";
		}

		log.info(form.toString());

		//画像データをフォームから取得し設定
		message.setImage(form.getMultiPartFile().getBytes());
		service.insertMessage(message, form, loginUser, room.getId());

		return "redirect:/rooms/{roomId}";
	}
}
