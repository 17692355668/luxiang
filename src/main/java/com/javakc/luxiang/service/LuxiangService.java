package com.javakc.luxiang.service;


import com.javakc.luxiang.entity.Luxiang;

import java.util.List;

public interface LuxiangService {
    int insert(String ip,String filename,String file);
    Luxiang selAllService(String file);
}
