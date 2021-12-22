package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.TRoomUser;

@Mapper
public interface RoomUserMapper {

	public int insertRoomUser(TRoomUser roomUser);

}
