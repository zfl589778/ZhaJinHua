package com.eric.inter;

import java.util.List;

import com.eric.components.CommonResult;
import com.eric.inter.entity.cache.Room;

public interface IRoomService {

	CommonResult<Room> enterRoom(Integer number,String allowPassword,Long allowBasePoint,String sessionId);
	
	CommonResult<Room> createRoom(Integer number,String name,String password,Long basePoint,Long maxOrderLimit,Integer maxRoundLimit);
	
	CommonResult<List<Room>> getRoomList(Integer number);
}
