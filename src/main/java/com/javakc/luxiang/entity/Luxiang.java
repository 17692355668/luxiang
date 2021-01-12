package com.javakc.luxiang.entity;

/**
 * @program:luxiang
 * @description:
 * @create:2021-01-11
 */
public class Luxiang {
    private int id;
    private  String ip;
    private  String filename;
    private  String file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Luxiang{" +
                "ip='" + ip + '\'' +
                ", filename='" + filename + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
