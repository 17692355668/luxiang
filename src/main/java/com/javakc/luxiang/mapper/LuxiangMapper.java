package com.javakc.luxiang.mapper;

import com.javakc.luxiang.entity.Luxiang;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:luxiang
 * @description:
 * @create:2021-01-11
 */

@Repository
public interface LuxiangMapper {
    int insert(String ip,String filename,String file);


    Luxiang selAll(String file);

}
