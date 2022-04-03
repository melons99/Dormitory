package com.melons.mapper;

import com.melons.pojo.Outsiders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OutsidersMapper {

    void insert(Outsiders outsiders);
    void update(Map map);

    List<Outsiders> findAll();
}
