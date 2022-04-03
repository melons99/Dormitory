package com.melons.mapper;

import com.melons.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    void insert(Student student);
    void update(Student student);
    void deleteById(String number);
    List<Student> findAll();
    List<Student> selectByNumber(String number);
    List<Student> selectByName(String name);
    List<Student> selectByBuildingNo(String buildingNo);
    List<Student> selectBySet(String dept,String grade,String classes);
}


