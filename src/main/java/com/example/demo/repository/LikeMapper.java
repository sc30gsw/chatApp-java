package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.TLike;

@Mapper
public interface LikeMapper {

	public void insertLike(TLike like);
	
	public List<TLike> likeCorrespondingMessage(int userId);
	
	public int likesCount(int messageId);
	
	public void deleteLikeOne(@Param("userId")int userId, @Param("messageId")int messageId);
}
