package com.javakc.luxiang.service.impl;

import com.javakc.luxiang.entity.Luxiang;
import com.javakc.luxiang.mapper.LuxiangMapper;
import com.javakc.luxiang.service.LuxiangService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


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


     /**
     2      * 调用流程上传文件接口上传文件
     3      * @param url
     4      * @param path
     5      * @return
     6      */
     @Override
     public  String sendPostUplodFile(String path) {
                DataOutputStream out = null;
                BufferedReader in = null;
                 String result = "";
                 try {
                        URL realUrl = new URL("http://192.9.100.254:9999/upload");
                        //打开和URL之间的连接
                        HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
                        //发送POST请求必须设置如下两行
                        conn.setDoOutput(true);
                        conn.setDoInput(true);

                        String BOUNDARY = "----WebKitFormBoundary07I8UIuBx6LN2KyY";
                        conn.setUseCaches(false);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("connection", "Keep-Alive");
            //            conn.setRequestProperty("user-agent", "Mozilla/4.0 (conpatible; MSIE 6.0; Windows NT 5.1; SV1)");
                        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");
                        conn.setRequestProperty("Charsert", "UTF-8");
                        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                        conn.connect();

                        out = new DataOutputStream(conn.getOutputStream());
                        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
                         //添加参数
                         StringBuffer sb1 = new StringBuffer();
                         sb1.append("--");
                         sb1.append(BOUNDARY);
                         sb1.append("\r\n");
                         sb1.append("Content-Disposition: form-data;name=\"luid\"");
                         sb1.append("\r\n");
                         sb1.append("\r\n");
                         sb1.append("123");
                         sb1.append("\r\n");
                         out.write(sb1.toString().getBytes());
                         //添加参数file
                         File file = new File(path);
                         StringBuffer sb = new StringBuffer();
                         sb.append("--");
                         sb.append(BOUNDARY);
                         sb.append("\r\n");
                         sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"");
                         sb.append("\r\n");
                         sb.append("Content-Type: application/octet-stream");
                         sb.append("\r\n");
                         sb.append("\r\n");
                         out.write(sb.toString().getBytes());

                         DataInputStream in1 = new DataInputStream(new FileInputStream(file));
                         int bytes = 0;
                         byte[] bufferOut = new byte[1024];
                         while ((bytes = in1.read(bufferOut)) != -1) {
                                 out.write(bufferOut,0,bytes);
                             }
                         out.write("\r\n".getBytes());
                         in1.close();
                         out.write(end_data);

                         //flush输出流的缓冲
                         out.flush();
                         //定义BufferedReader输入流来读取URL的响应
                         in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                         String line;
                         while ((line = in.readLine()) != null) {
                                 result += line;
                             }
                     } catch (Exception e) {
                         // TODO Auto-generated catch block

                         e.printStackTrace();
                     }finally {
                         try {
                                 if (out != null) {
                                        out.close();
                                    }
                                 if (in != null) {
                                        in.close();
                                     }
                                 File file=new File(path);
                                 file.delete();
                             } catch (Exception ex) {
                                 // TODO: handle exception
                                 ex.printStackTrace();
                             }

                     }


         return result;
             }

     }



