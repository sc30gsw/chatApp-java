package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.TRoomUser;

@Mapper
public interface RoomUserMapper {

	/**room_users登録*/
	public int insertRoomUser(TRoomUser roomUser);
	
	/**room_users取得(1件)*/
	public TRoomUser findRoomUserOne(int roomId);
}
