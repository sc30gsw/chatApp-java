package com.example.demo.service;

import com.example.demo.entity.MUser;

public interface UserService {

	/**ユーザー登録*/
	public void signup(MUser user);
	
	/**ログインユーザー取得*/
	public MUser getLoginUser(String email);
}
