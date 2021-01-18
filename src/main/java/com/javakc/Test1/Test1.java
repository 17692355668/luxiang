package com.javakc.Test1;

import com.sun.jna.NativeLong;
import java.io.File;
import java.util.Date;

public class Test1 {
    private HCNetSDK sdk = HCNetSDK.INSTANCE;
    private  NativeLong lUserID;//用户句柄
    private NativeLong lRealHandle;
    private HCNetSDK.NET_DVR_DEVICEINFO_V30 deviceInfo;

    private  String m_sDeviceIP = "";//设备ip地址
    private  String m_sUsername = "admin";//设备ip地址
    private  String m_sPassword = "";//设备ip地址
    private  String file="";


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
        sdk.NET_DVR_Init();
    }


    public void zhuxiao() {
        sdk.NET_DVR_Logout(lUserID);
        sdk.NET_DVR_Cleanup();
    }

    public void zhece(String ip) {
        m_sDeviceIP = ip;
        deviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        lUserID = sdk.NET_DVR_Login_V30(ip, (short) 8000, m_sUsername, m_sPassword, deviceInfo);

        if (0 != lUserID.longValue()) {
            System.out.println(sdk.NET_DVR_GetLastError());
        }
        System.out.println(ip);
        System.out.println(lUserID.longValue());
    }

    public String kaishi() {
        //获取 实时预览  的返回值

        HCNetSDK.NET_DVR_CLIENTINFO nInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
        nInfo.hPlayWnd=null;
        nInfo.lChannel = new NativeLong(1);
        nInfo.lLinkMode = new NativeLong(0x80000000);
        nInfo.sMultiCastIP=null;
        lRealHandle = sdk.NET_DVR_RealPlay(lUserID, nInfo);
        System.out.println(lRealHandle);
        //捕获数据并存放到指定的文件中
        //用当前时间戳来命名视频名字
        Date dt=new Date();

        String year=String.format("%tY", dt);

        String mon=String .format("%tm", dt);

        String day=String .format("%td", dt);
        String file1="/root/store/"+year+"/"+mon+"/"+day;
        File file2 = new File(file1);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String time =java.util.UUID.randomUUID().toString();
        file = file1+"/" + time + ".mp4";
        sdk.NET_DVR_SaveRealData(lRealHandle, file);
        return file;
    }

    public void jieshu() {
        //结束录像
        sdk.NET_DVR_StopSaveRealData(lRealHandle);
        System.out.println("录像结束");
        sdk.NET_DVR_StopRealPlay(lRealHandle);
    }



}//Test1  Class结束
