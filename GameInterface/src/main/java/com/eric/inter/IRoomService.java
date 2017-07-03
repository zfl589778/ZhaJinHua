package com.eric.inter;

import java.util.List;

import com.eric.components.CommonResult;
import com.eric.inter.entity.Room;

public interface IRoomService {

	CommonResult<Room> enterRoom(String id,String allowPassword,Integer allowBasePoint,String sessionId);
	
	CommonResult<Room> createRoom(String id,String number,String name,String password,Integer basePoint,Integer maxOrderLimit,Integer maxRoundLimit);
	
	CommonResult<List<Room>> getRoomList(String number);
}
