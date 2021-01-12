package com.javakc.luxiang.service.impl;

import com.javakc.luxiang.entity.Luxiang;
import com.javakc.luxiang.mapper.LuxiangMapper;
import com.javakc.luxiang.service.LuxiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuxiangServiceImpl implements LuxiangService {

    @Autowired
    LuxiangMapper luxiangMapper;

    @Override
    public int insert(String ip,String filename,String file) {
        return luxiangMapper.insert( ip, filename, file);
    }
    @Override
    public Luxiang selAllService(String file) {
        Luxiang luxiangs = luxiangMapper.selAll(file);

        return luxiangs;
    }
}
