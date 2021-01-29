package com.javakc.Test1;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import javax.swing.*;
import java.io.File;
import java.util.Date;

import static com.javakc.Test1.HCNetSDK.NET_DVR_SET_COMPRESSCFG_V30;

public class Test1 {
    private  HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    private  int lUserID = -1;//用户句柄
    private  int lRealHandle = 0;
    private  int iCharEncodeType = 0;//设备字符集
    private  String m_sDeviceIP = "";//设备ip地址
    private  String m_sUsername = "admin";//设备用户名
    private  String m_sPassword = "";//设备密码
    private  String file="";
    private String filename="";

    private HCNetSDK.NET_DVR_PICCFG_V30 m_struPicCfg;//图片参数
    private HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg;//压缩参数
    private HCNetSDK.NET_DVR_RECORD_V30 m_struRemoteRecCfg;//录像参数
    private HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    private HCNetSDK.NET_DVR_SHOWSTRING_V30 m_strShowString;//叠加字符参数







    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getlUserID() {
        return lUserID;
    }

    public void setlUserID(int lUserID) {
        this.lUserID = lUserID;
    }

    public String getM_sDeviceIP() {
        return m_sDeviceIP;
    }

    public void setM_sDeviceIP(String m_sDeviceIP) {
        this.m_sDeviceIP = m_sDeviceIP;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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
        String file1="E:\\";
        File file2 = new File(file1);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String time =java.util.UUID.randomUUID().toString();
        filename=time+".mp4";
        file = file1+time + ".mp4";
        hCNetSDK.NET_DVR_SaveRealData(lRealHandle, file);
        return filename;
    }

    public void jieshu() {
        //结束录像
        hCNetSDK.NET_DVR_StopSaveRealData(lRealHandle);
        hCNetSDK.NET_DVR_StopRealPlay(lRealHandle);
    }


}//Test1  Class结束
