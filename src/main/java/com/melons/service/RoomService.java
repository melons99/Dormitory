package com.melons.service;

import com.melons.pojo.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface RoomService {
    void insert(Room room);
    void update(String buildname,String roomname,String number);
    void add(String buildname,String roomname);
    void del(String buildname,String roomname);
    void delete(Map map);
    List<Room> findAllRooms();
    List<Room> findByBuild(String buildname);
    List<Room> findByName(Map map);
    List<Room> selectBySet(String buildname,String roomname);
}
