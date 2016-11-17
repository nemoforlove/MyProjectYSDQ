package com.gpf.myprojectysdq.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/15.
 */

@Table(name = "Search")
public class Search implements Serializable {

    @Column(name="_id",isId = true,autoGen = true)
    private int id;
    @Column(name = "name")
    private String name ;

    public Search() {
    }

    public Search(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Search{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
