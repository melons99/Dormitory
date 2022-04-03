package com.melons.mapper;

import com.melons.pojo.Build;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BuildMapper {
    void insert(Build build);
    void delete(String buildname);
    List<Build> findAllBuilds();
    List<Build> findByName(String buildName);
}
