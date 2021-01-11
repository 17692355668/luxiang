package com.javakc.luxiang.service.impl;

import com.javakc.luxiang.mapper.LuxiangMapper;
import com.javakc.luxiang.service.LuxiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuxiangServiceImpl implements LuxiangService {

    @Autowired
    LuxiangMapper luxiangMapper;

    @Override
    public int insert(int id) {
        return luxiangMapper.insert(id);
    }
}
