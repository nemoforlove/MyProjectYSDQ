package com.gpf.myprojectysdq.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/12.
 */
@Table(name="Video")
public class Video implements Serializable {

    @Column(name="_id",isId = true,autoGen = true)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="name")
    private long duration;
    @Column(name="name")
    private long size;
    @Column(name="name")
    private String path;

    public Video() {
    }

    public Video(String name, long duration, long size, String path) {
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", path='" + path + '\'' +
                '}';
    }
}
