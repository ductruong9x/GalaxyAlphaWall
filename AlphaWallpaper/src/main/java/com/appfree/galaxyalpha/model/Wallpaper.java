package com.appfree.galaxyalpha.model;

/**
 * Created by truongtvd on 8/6/14.
 */
public class Wallpaper {
    private String name;
    private int wall;

    public Wallpaper(String name, int wall) {
        this.name = name;
        this.wall = wall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWall() {
        return wall;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }
}
