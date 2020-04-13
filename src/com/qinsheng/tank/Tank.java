package com.qinsheng.tank;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Tank {

    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 4;

    private boolean moving = false;

    private TankFrame tankFrame = null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics graphics){
        switch (dir) {
            case LEFT:
                graphics.drawImage(ResourceManager.tankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceManager.tankR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceManager.tankD, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceManager.tankU, x, y, null);
                break;
        }


        move();

    }

    public void fire() {
        tankFrame.bulletList.add(new Bullet(this.x, this.y, this.dir, this.tankFrame));
    }

    private void move() {
        if(!moving) return;
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
    }
}
