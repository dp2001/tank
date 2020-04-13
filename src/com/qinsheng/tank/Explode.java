package com.qinsheng.tank;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Explode {

    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    public static int HEIGHT = ResourceManager.explodes[0].getHeight();

    private int x, y;

    TankFrame tankFrame = null;
    private boolean living = true;

    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }


    public void paint(Graphics graphics) {

        graphics.drawImage(ResourceManager.explodes[step++], x, y, null);
        if(step >= ResourceManager.explodes.length){
            step = 0;
        }
    }
}
