package com.javakc.luxiang.controller;

import com.javakc.luxiang.service.impl.LuxiangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program:luxiang
 * @description:
 * @create:2021-01-11
 */
@Controller
@RequestMapping("luxiang")
public class LuxiangController {

    @Autowired
    LuxiangServiceImpl luxiangService;

    @RequestMapping("insert/{id}")
    public void insert(@PathVariable int id)
    {
        int x=luxiangService.insert(id);
        System.out.println(x);
    }

}
