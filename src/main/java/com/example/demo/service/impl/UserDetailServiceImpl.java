package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper mapper;
	
	 /**
	   * メールアドレスで検索したユーザーのuserエンティティをUserDetailServiceImpllクラスのインスタンスへ変換する
	   *
	   * @param email 検索するユーザーのメールアドレス
	   * @return メールアドレスで検索できたユーザーのユーザー情報
	   * @throws UsernameNotFoundException メールアドレスでユーザーが検索できなかった場合にスローする。
	   */
	  @Transactional(readOnly = true)
	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    assert(email != null);
	    log.debug("loadUserByUsername(email):[{}]", email);
	    return mapper.findByEmail(email)
	        .map(UserDetailServiceImpll::new)
	        .orElseThrow(() -> new UsernameNotFoundException("User not found by email:[" + email + "]"));
	  }
}
