package com.javakc.luxiang.service;


import com.javakc.luxiang.entity.Luxiang;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LuxiangService {
    int insert(String ip,String filename,String file);
    Luxiang selAllService(String file);
     String sendPostUplodFile(String path);
}
