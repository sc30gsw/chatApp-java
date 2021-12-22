package com.example.demo.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.demo.entity.TRoomUser;
import com.example.demo.form.RoomForm;
import com.example.demo.service.impl.UserDetailServiceImpll;

public interface RoomUserService {

	/**room_user登録*/
	public void registRoomUser(RoomForm form, TRoomUser roomUser, @AuthenticationPrincipal UserDetailServiceImpll loginUser);
}
