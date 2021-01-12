package com.javakc.luxiang.controller;

import com.javakc.luxiang.entity.Luxiang;
import com.javakc.luxiang.service.impl.LuxiangServiceImpl;
import com.javakc.test.Test1.Test1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @program:luxiang
 * @description:
 * @create:2021-01-11
 */
@Controller
public class LuxiangController {


    @Autowired
    LuxiangServiceImpl luxiangService;
    Test1 test=new Test1();

    //保存
    @RequestMapping("insert/{ip}/{filename}/{file}")
    public void insert(@PathVariable String ip,@PathVariable String filename,@PathVariable String file)
    {
        int x=luxiangService.insert(ip,filename,file);
    }

    //初始化
    //参数：ip
    @RequestMapping("chushihua/{ip}")
    public void chushihua(@PathVariable String ip)
    {
        test.chushihua();
        test.zhece(ip);
    }

    //开始录像
    //返回绝对路径
    @RequestMapping("kaishi")
    public String kaishi()
    {
        String file=test.kaishi();
        return file;
    }

    //结束录像
    @RequestMapping("jieshu")
    public void jieshu()
    {
        test.jieshu();
        //信息储存
        insert(test.getM_sDeviceIP(),test.getFile(),test.getFile());
    }

    //注销
    @RequestMapping("zhuxiao")
    public void zhuxiao()
    {
        test.zhuxiao();
    }


    //根据文件名进行查询
    @ResponseBody
    @RequestMapping("select")
    public Luxiang sel(String file){
        Luxiang list=luxiangService.selAllService( file);
        return list;

    }



}
