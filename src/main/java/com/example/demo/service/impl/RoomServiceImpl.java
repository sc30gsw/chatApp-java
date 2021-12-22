package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
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
}
