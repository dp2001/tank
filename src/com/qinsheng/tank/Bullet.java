package com.qinsheng.tank;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Bullet {

    private static final int SPEED = 10;

    public static int WIDTH = ResourceManager.bulletD.getWidth();
    public static int HEIGHT = ResourceManager.bulletD.getHeight();

    private int x, y;
    private Dir dir;
    TankFrame tankFrame = null;

    private boolean live = true;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }


    public void paint(Graphics graphics) {
        if(!live) {
            tankFrame.bulletList.remove(this);
        }

        switch (dir) {
            case LEFT:
                graphics.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceManager.bulletD, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceManager.bulletU, x, y, null);
                break;
        }

        move();
    }

    private void move(){
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
    }
}
