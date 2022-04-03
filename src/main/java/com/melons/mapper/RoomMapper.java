package com.melons.mapper;

import com.melons.pojo.Build;
import com.melons.pojo.Room;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoomMapper {
    void insert(Room room);
    void update(String buildname,String roomname,String number);
    void add(String buildname,String roomname);
    void del(String buildname,String roomname);
    void delete(Map map);
    List<Room> findAllRooms();
    List<Room> findByBuild(String buildname);
    Room findByName(String buildname,String roomname);
    List<Room> selectBySet(String buildname,String roomname);

}
