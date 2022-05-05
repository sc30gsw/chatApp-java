package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.demo.entity.MRoom;
import com.example.demo.entity.MessagesDetail;
import com.example.demo.form.RoomForm;
import com.example.demo.service.impl.UserDetailServiceImpll;

public interface RoomService {

	/**チャットルーム登録*/
	public void insertRoom(MRoom room, RoomForm form);
	
	/**ログインユーザーと選択されたユーザーのチャットルーム複数件取得*/
	public List<MRoom> getLoginUserRooms(@AuthenticationPrincipal UserDetailServiceImpll loginUser);
	
	/**チャットルーム取得(1件)*/
	public MRoom getRoomOne(int id);
	
	/**チャットルームに紐づくメッセージ取得*/
	public List<MessagesDetail> getMessagesAll(int id);
	
	/**チャットルーム(1件)削除*/
	public void deleteRoomMessageOne(int id);
	
}
