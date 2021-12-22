package com.example.demo.service;

import com.example.demo.entity.MRoom;
import com.example.demo.form.RoomForm;

public interface RoomService {

	/**チャットルーム登録*/
	public void insertRoom(MRoom room, RoomForm form);
	
}
