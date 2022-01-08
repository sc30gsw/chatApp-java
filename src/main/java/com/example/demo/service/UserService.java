package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.demo.entity.MUser;
import com.example.demo.form.UserEditForm;
import com.example.demo.service.impl.UserDetailServiceImpll;

public interface UserService {

	/**ユーザー登録*/
	public void signup(MUser user);
	
	/**ログインユーザー取得*/
	public MUser getLoginUser(String email);
	
	/**ログインユーザー以外のユーザー取得(複数件)*/
	public List<MUser> getUsers(int id);
	
	/**ユーザー更新(1件)*/
	public void updateUserOne(@AuthenticationPrincipal UserDetailServiceImpll loginUser, UserEditForm form);
}
