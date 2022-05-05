package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.MRoom;
import com.example.demo.entity.MessagesDetail;

@Mapper
public interface RoomMapper {
	
	/**チャットルーム登録*/
	public int insertOneRoom(MRoom room);
	
	/**ログインユーザーと選択されたユーザーのチャットルーム複数件取得*/
	public List<MRoom> findLoginUserRooms(int currentUserId);

	/**チャットルーム(1件)取得*/
	public MRoom findRoomOne(int id);
	
	/**チャットルームに紐づくメッセージ取得*/
	public List<MessagesDetail> findMessagesAll(int id);
	
	/**チャットルーム(1件)削除*/
	public int deleteRoomOne(int id);
}
