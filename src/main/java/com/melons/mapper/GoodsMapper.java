package com.melons.mapper;

import com.melons.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {
    void insert(Goods goods);
    void delete(int no);

    List<Goods> findAll();
}
