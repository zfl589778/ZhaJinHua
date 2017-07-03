package com.eric.service.bus.lobby.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.components.CommonResult;
import com.eric.components.Exceptions;
import com.eric.inter.IRoomService;
import com.eric.inter.entity.Room;
import com.eric.inter.entity.User;
import com.eric.service.cache.RedisCacheHelper;
import com.eric.utils.MD5Utils;
import com.eric.utils.StringUtils;

@Service
public class RoomServiceImpl implements IRoomService{

	@Autowired
	private RedisCacheHelper redisCacheHelper;
	
	@Override
	public CommonResult<Room> enterRoom(String id,String allowPassword,Integer allowBasePoint,String sessionId) {
		Room room = redisCacheHelper.get("room_"+id);
		if(room==null) 
			Exceptions.throwException("该房间不存在");
		if(!StringUtils.isBlank(room.getPassword())){
			//加密房间
			if(!room.getPassword().equals(allowPassword)) 
				Exceptions.throwException("房间密码错误");
		}
		//验证用户级别
		if(allowBasePoint<room.getBasePoint()) 
			Exceptions.throwException("用户级别不够");
		//存进入房间状态，要修改成通过sessionId存用户整体
		redisCacheHelper.lpush(id, sessionId);
		//获得该房间所有用户
		List<User> u_list = new LinkedList<User>();
		List<String> sids = redisCacheHelper.lrange(id);
		for(String sid:sids){
			User u = redisCacheHelper.get(sid);
			u_list.add(u);
		}
		room.setUsers(u_list);
		return CommonResult.returnSuccess(room);
	}

	@Override
	public CommonResult<Room> createRoom(String id,String number,String name,String password,Integer basePoint,Integer maxOrderLimit,Integer maxRoundLimit) {
		Room room = new Room();
		room.setId(id);
		room.setNumber(number);
		room.setName(name);
		if(!StringUtils.isBlank(password)){
			room.setPassword(MD5Utils.MD5(password));
		}
		room.setBasePoint(basePoint);
		room.setMaxOrderLimit(maxOrderLimit);
		room.setMaxRoundLimit(maxRoundLimit);
		//存入缓存
		redisCacheHelper.put("room_"+id, room);
		//存入房间号索引
		redisCacheHelper.lpush("room_index", id);
		return CommonResult.returnSuccess(room);
	}

	@Override
	public CommonResult<List<Room>> getRoomList(String number) {
		List<Room> list = new LinkedList<Room>();
		List<String> id_list = redisCacheHelper.lrange("room_index");
		for (String id : id_list) {
			Room room = redisCacheHelper.get("room_"+id);
			if(!StringUtils.isBlank(number)){
				if(number.equals(room.getNumber())){
					list.add(room);
				}
			}else{
				list.add(room);
			}
		}
		return CommonResult.returnSuccess(list);
	}

}
