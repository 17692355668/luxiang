package com.javakc.test.Test1;

import com.javakc.luxiang.controller.LuxiangController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

public class Test1 {
    private static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    private static int lUserID = -1;//用户句柄
    private static int lRealHandle = 0;
    private static int iCharEncodeType = 0;//设备字符集
    private static String m_sDeviceIP = "";//设备ip地址
     static String m_sUsername = "admin";//设备用户名
    private static String m_sPassword = "";//设备密码
     static String file="";

    public static String getM_sDeviceIP() {
        return m_sDeviceIP;
    }

    public static void setM_sDeviceIP(String m_sDeviceIP) {
        Test1.m_sDeviceIP = m_sDeviceIP;
    }

    public static String getFile() {
        return file;
    }

    public static void setFile(String file) {
        Test1.file = file;
    }

    public void chushihua()
    {
        hCNetSDK.NET_DVR_Init();
    }


    public void zhuxiao() {
        hCNetSDK.NET_DVR_Logout(lUserID);
        hCNetSDK.NET_DVR_Cleanup();
    }

    public void zhece(String ip) {
        m_sDeviceIP = ip;
        //注册
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();//设备登录信息

        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());

        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());

        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());

        m_strLoginInfo.wPort = 8000;
        m_strLoginInfo.bUseAsynLogin = false; //是否异步登录：0- 否，1- 是
        m_strLoginInfo.write();

        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();//设备信息
        lUserID = hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        if (lUserID == -1) {
            System.out.println("登录失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            System.out.println("登录成功！");
            iCharEncodeType = m_strDeviceInfo.byCharEncodeType;
        }
    }

    public String kaishi() {
        //获取 实时预览  的返回值
        HCNetSDK.NET_DVR_CLIENTINFO lpClintInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
        lpClintInfo.lChannel = 1;
        lpClintInfo.lLinkMode = 0;
        lpClintInfo.hPlayWnd = null;
        lRealHandle = hCNetSDK.NET_DVR_RealPlay(lUserID, lpClintInfo);
        //捕获数据并存放到指定的文件中
        //用当前时间戳来命名视频名字
        long time = System.currentTimeMillis();
        file = "E:\\qq\\" + time + ".mp4";
        hCNetSDK.NET_DVR_SaveRealData(lRealHandle, file);
        return file;
    }

    public void jieshu() {
        //结束录像
        hCNetSDK.NET_DVR_StopSaveRealData(lRealHandle);
        System.out.println("录像结束");
        hCNetSDK.NET_DVR_StopRealPlay(lRealHandle);
    }


}//Test1  Class结束
