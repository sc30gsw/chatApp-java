package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.MUser;
import com.example.demo.form.UserEditForm;
import com.example.demo.repository.UserMapper;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	@Override
	public void signup(MUser user) {	
		//パスワードの暗号化
		String rawPassword = user.getPassword();
		String rawPasswordConfirmation = user.getPasswordConfirmation();
		user.setPassword(encoder.encode(rawPassword));
		user.setPasswordConfirmation(encoder.encode(rawPasswordConfirmation));
		
		user.setRole("ROLE_GENERAL");
		
		//現在時刻の取得
		LocalDateTime now = LocalDateTime.now();
		user.setCreatedAt(now);
		user.setUpdatedAt(now);
		
		mapper.insertOne(user);
	}
	
	/**ログインユーザーをメールアドレスで検索*/
	@Override
	public MUser getLoginUser(String email) {		
		Optional<MUser> option = mapper.findByEmail(email);
		MUser user = option.orElse(null);
		return user;
	}
	
	/**ログインユーザー以外のユーザー取得(複数件)*/
	@Override
	public  List<MUser> getUsers(int id) {
		return mapper.findMany(id);
	}
	
	@Transactional
	@Override
	public void updateUserOne(@AuthenticationPrincipal UserDetailServiceImpll loginUser, UserEditForm form) {
		//ログインユーザーID取得
		int id = loginUser.getUser().getId();
		
		//フォームから値を取得
		String name = form.getName();
		String email = form.getEmail();
		
		mapper.updateUser(id, name, email);
	}
}
