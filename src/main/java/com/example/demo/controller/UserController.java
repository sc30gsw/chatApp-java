package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MUser;
import com.example.demo.form.SignupForm;
import com.example.demo.form.UserEditForm;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserDetailServiceImpll;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/signup")
	public String getSignup(@ModelAttribute("form") SignupForm form) {

		return "user/signup";
	}

	/**
	 * ユーザー登録処理
	 */
	@PostMapping("/signup")
	public String postSignup(Model model, @Validated @ModelAttribute("form") SignupForm form, BindingResult result) {

		// 入力チェック
		if (result.hasErrors()) {
			/* NG:ユーザー登録画面に戻る*/
			return "user/signup";
		}

		log.info(form.toString());

		//formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);

		//ユーザー登録
		userService.signup(user);

		return "redirect:/";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "user/login";
	}

	/**
	 * ログイン機能
	 */
	@PostMapping("/login")
	public String postLogin() {
		log.info("ログイン");
		return "redirect:/";//マイページのリンクを設定予定
	}

	@GetMapping("/edit/{id}")
	public String getEditUser(Model model, @ModelAttribute("form") UserEditForm form,
			@AuthenticationPrincipal UserDetailServiceImpll loginUser, @PathVariable("id") int id) {
		MUser user = loginUser.getUser();
		model.addAttribute("user", user);

		if (id != user.getId()) {
			return "redirect:/";
		}
		return "user/edit";
	}

	@PostMapping(value = "/edit/{id}/update", params = "update")
	public String postUserUpdate(@Validated @ModelAttribute("form") UserEditForm form,
			BindingResult result, @AuthenticationPrincipal UserDetailServiceImpll loginUser) {
		
		if (result.hasErrors()) {
			return "redirect:/edit/{id}";
		}

		userService.updateUserOne(loginUser, form);

		return "redirect:/login";
	}

}
