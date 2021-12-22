package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.MUser;

public interface UserService {

	/**ユーザー登録*/
	public void signup(MUser user);
	
	/**ログインユーザー取得*/
	public MUser getLoginUser(String email);
	
	/**ログインユーザー以外のユーザー取得(複数件)*/
	public List<MUser> getUsers(int id);
}
