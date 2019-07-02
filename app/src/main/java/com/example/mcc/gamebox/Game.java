package com.example.mcc.gamebox;

// 定义一个实体类，作为RecyclerView适配器的适配类型
public class Game {
    private String name;
    private int imageId;

    public Game(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}