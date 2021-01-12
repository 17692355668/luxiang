package com.javakc.luxiang.controller;

import com.javakc.luxiang.entity.Luxiang;
import com.javakc.luxiang.service.impl.LuxiangServiceImpl;
import com.javakc.test.Test1.Test1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @program:luxiang
 * @description:
 * @create:2021-01-11
 */
@RestController
public class LuxiangController {


    @Autowired
    LuxiangServiceImpl luxiangService;
    Map<String,Test1> vedioMap = new HashMap<>();

    //保存
    public void insert(@PathVariable String ip,@PathVariable String filename,@PathVariable String file)
    {
        int x=luxiangService.insert(ip,filename,file);
    }
    //初始化
    //参数：ip
    @RequestMapping("chushihua/{ip}")
    public void chushihua(@PathVariable String ip)
    {
        Test1 test1 = vedioMap.get(ip);
        if(test1==null){
            test1=new Test1();
            vedioMap.put(ip,test1);
        }
        test1.chushihua();
        test1.zhece(ip);
    }

    //开始录像
    //返回绝对路径
    @RequestMapping("kaishi/{ip}")
    public String kaishi(@PathVariable String ip)
    {
        Test1 test1 = vedioMap.get(ip);
        String file=test1.kaishi();
        return file;
    }

    //结束录像
    @RequestMapping("jieshu/{ip}")
    public void jieshu(@PathVariable String ip)
    {
        Test1 test1 = vedioMap.get(ip);
        test1.jieshu();
        //信息储存
        insert(test1.getM_sDeviceIP(),test1.getFile(),test1.getFile());
    }

    //注销
    @RequestMapping("zhuxiao/{ip}")
    public void zhuxiao(@PathVariable String ip)
    {
        Test1 test1 = vedioMap.get(ip);
        test1.zhuxiao();
    }








}
