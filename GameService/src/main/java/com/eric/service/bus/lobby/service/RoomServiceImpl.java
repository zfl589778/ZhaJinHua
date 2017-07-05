package com.eric.service.bus.lobby.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.components.CommonResult;
import com.eric.components.Exceptions;
import com.eric.inter.IRoomService;
import com.eric.inter.entity.cache.Player;
import com.eric.inter.entity.cache.Room;
import com.eric.service.cache.RedisCacheHelper;
import com.eric.utils.MD5Utils;
import com.eric.utils.StringUtils;

@Service
public class RoomServiceImpl implements IRoomService{

	@Autowired
	private RedisCacheHelper redisCacheHelper;
	
	@Override
	public CommonResult<Room> enterRoom(Integer number,String allowPassword,Long allowBasePoint,String sessionId) {
		Room room = redisCacheHelper.get("room_"+number);
		if(room==null) 
			Exceptions.throwException("该房间不存在");
		if(!StringUtils.isBlank(room.getPassword())){
			//加密房间
			if(!room.getPassword().equals(allowPassword)) 
				Exceptions.throwException("房间密码错误");
		}
		//验证用户级别
		if(allowBasePoint<room.getBaseOrder()) 
			Exceptions.throwException("用户级别不够");
		//存进入房间状态，要修改成通过sessionId存用户整体
		redisCacheHelper.lpush(number.toString(), sessionId);
		//获得该房间所有玩家
		List<Player> u_list = new LinkedList<Player>();
		List<String> sids = redisCacheHelper.lrange(number.toString());
		for(String sid:sids){
			Player p = redisCacheHelper.get(sid);
			u_list.add(p);
		}
		room.setPlayerList(u_list);
		return CommonResult.returnSuccess(room);
	}

	@Override
	public CommonResult<Room> createRoom(Integer number,String name,String password,Long basePoint,Long maxOrderLimit,Integer maxRoundLimit) {
		Room room = new Room();
		room.setNumber(number);
		room.setName(name);
		if(!StringUtils.isBlank(password)){
			room.setPassword(MD5Utils.MD5(password));
		}
		room.setBaseOrder(basePoint);
		room.setMaxOrder(maxOrderLimit);
		room.setTotalRound(maxRoundLimit);
		//存入缓存
		redisCacheHelper.put("room_"+number, room);
		//存入房间号索引
		redisCacheHelper.lpush("room_index", number.toString());
		return CommonResult.returnSuccess(room);
	}

	@Override
	public CommonResult<List<Room>> getRoomList(Integer number) {
		List<Room> list = new LinkedList<Room>();
		List<String> id_list = redisCacheHelper.lrange("room_index");
		for (String id : id_list) {
			Room room = redisCacheHelper.get("room_"+id);
			if(number!=null){
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
