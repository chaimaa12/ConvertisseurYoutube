package com.example.admin.convertisseuryoutube.model;

import java.io.Serializable;

/**
 * Created by Sriyou on 18/04/2017.
 */

public class Music implements Serializable {

    private String name;
    private String lenght;
    private String link;

    public Music() {
    }

    public Music(String name, String lenght, String link) {
        this.name = name;
        this.lenght = lenght;
        this.link = link;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}


