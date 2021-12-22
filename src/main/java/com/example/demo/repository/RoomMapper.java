package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.MRoom;

@Mapper
public interface RoomMapper {
	
	/**チャットルーム登録*/
	public int insertOneRoom(MRoom room);

}
