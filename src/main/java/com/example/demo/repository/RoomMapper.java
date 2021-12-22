package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.MRoom;

@Mapper
public interface RoomMapper {
	
	/**チャットルーム登録*/
	public int insertOneRoom(MRoom room);
	
	/**ログインユーザーのチャットルーム複数件取得*/
	public List<MRoom> findLoginUserRooms(int currentUserId);

}
