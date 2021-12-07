package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MUser;
import com.example.demo.form.SignupForm;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute("form") SignupForm form) {
		
		return "user/signup";
	}
	
	/**
	 * ユーザー登録処理
	 */
	@PostMapping("/signup")
	public String postSignup(Model model, @Validated @ModelAttribute("form") SignupForm form, BindingResult result) {

		/* 入力チェック*/
		if (result.hasErrors()) {
			/* NG:ユーザー登録画面に戻る*/
			return "user/signup";
		}

		log.info(form.toString());
		
		//formをMUserクラスに変換
		ModelMapper modelMapper = new ModelMapper();
		MUser user = modelMapper.map(form, MUser.class);
		
		//ユーザー登録
		userService.signup(user);

		return "redirect:/";
	}
}
