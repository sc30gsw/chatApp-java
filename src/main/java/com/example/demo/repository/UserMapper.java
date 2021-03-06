package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.MUser;

@Mapper
public interface UserMapper {

	/**ユーザー登録*/
	public int insertOne(MUser user);
	
	/**ユーザー検索(Email)*/
	public Optional<MUser> findByEmail(String email);
	
	/**ログインユーザー以外のユーザー取得(複数件)*/
	public List<MUser> findMany(int id);
	
	/**ユーザー更新(1件)*/
	public void updateUser(@Param("id") int id, @Param("name") String name, @Param("email") String email);
	
}
