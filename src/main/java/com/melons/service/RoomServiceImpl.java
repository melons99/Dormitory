package com.melons.service;

import com.melons.mapper.RoomMapper;
import com.melons.mapper.StudentMapper;
import com.melons.pojo.Room;
import com.melons.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void insert(Room room) {
        roomMapper.insert(room);
    }

    @Override
    public void update(String buildname, String roomname, String number) {
        List<Student> students = studentMapper.selectByNumber(number);
        Student student = students.get(0);
        if(student.getBuildingNo().isEmpty()) {
            System.out.println("++++++++++++++++++++++++++++");
            roomMapper.update(buildname, roomname, number);
            roomMapper.add(buildname, roomname);
        } else {
            System.out.println("----------------------------");
            roomMapper.del(student.getBuildingNo(), student.getRoomNo());
            roomMapper.update(buildname, roomname, number);
            roomMapper.add(buildname, roomname);
        }

    }

    @Override
    public void add(String buildname, String roomname) {

    }

    @Override
    public void del(String buildname, String roomname) {

    }

    @Override
    public void delete(Map map) {
        roomMapper.delete(map);
    }

    @Override
    public List<Room> findAllRooms() {
        return null;
    }

    @Override
    public List<Room> findByBuild(String buildname) {
        return null;
    }

    @Override
    public List<Room> findByName(Map map) {
        return null;
    }

    @Override
    public List<Room> selectBySet(String buildname, String roomname) {
        return null;
    }
}
