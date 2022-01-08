package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.MRoom;
import com.example.demo.form.RoomForm;
import com.example.demo.repository.RoomMapper;
import com.example.demo.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomMapper mapper;

	/**
	 *チャットルーム登録
	 */
	@Transactional
	@Override
	public void insertRoom(MRoom room, RoomForm form) {
		//チャットルーム名取得
		room.setRoomName(form.getRoomName());

		//現在時刻の取得
		LocalDateTime now = LocalDateTime.now();
		room.setCreatedAt(now);

		//チャットルーム登録
		mapper.insertOneRoom(room);
	}

	/**
	 *ログインユーザーと選択されたユーザーのチャットルーム複数件取得
	 */
	@Override
	public List<MRoom> getLoginUserRooms(@AuthenticationPrincipal UserDetailServiceImpll loginUser) {
		//ログインユーザーのユーザーIDを取得
		int currentUserId = loginUser.getUser().getId();

		return mapper.findLoginUserRooms(currentUserId);
	}

	/**
	 *チャットルーム取得(1件)
	 */
	@Override
	public MRoom getRoomOne(int id) {
		return mapper.findRoomOne(id);
	}

}
