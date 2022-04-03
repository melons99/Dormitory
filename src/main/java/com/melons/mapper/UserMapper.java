package com.melons.mapper;

import com.melons.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> findAll();

    User findUser(User user);

    User loginUser(User user);

    void resigter(User user);

}
