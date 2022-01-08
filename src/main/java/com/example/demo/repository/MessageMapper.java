package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.TMessages;

@Mapper
public interface MessageMapper {

	/**メッセージ登録*/
	public int insertOneMessage(TMessages message);
}
