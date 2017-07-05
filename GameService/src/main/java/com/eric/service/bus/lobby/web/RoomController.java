package com.eric.service.bus.lobby.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eric.components.CommonResult;
import com.eric.inter.IRoomService;
import com.eric.inter.entity.cache.Room;

@Controller
public class RoomController {

	@Autowired
	private IRoomService roomService;
	
	@RequestMapping(value="/room/list")
	@ResponseBody
	public CommonResult<List<Room>> getRoomList(Integer number){
		CommonResult<List<Room>> result = roomService.getRoomList(number);
		return result;
	}
	
	@RequestMapping(value="/room/enter")
	@ResponseBody
	public CommonResult<Room> enterRoom(Integer number,String allowPassword,Long allowBasePoint,String sessionId){
		CommonResult<Room> result = roomService.enterRoom(number, allowPassword, allowBasePoint,sessionId);
		return result;
	}
	
	@RequestMapping(value="/room/create")
	@ResponseBody
	public CommonResult<Room> createRoom(Integer number,String name,String password,Long basePoint,Long maxOrderLimit,Integer maxRoundLimit){
		CommonResult<Room> result = roomService.createRoom(number, name, password, basePoint, maxOrderLimit, maxRoundLimit);
		return result;
	}
}
