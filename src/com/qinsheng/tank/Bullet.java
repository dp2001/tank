package com.qinsheng.tank;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Bullet {

    private static final int SPEED = 10;
    private static int WIDTH = 10, HEIGHT = 10;

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


    public void paint(Graphics g) {
        if(!live) {
            tankFrame.bulletList.remove(this);
        }
        Color c = g.getColor();

        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

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
