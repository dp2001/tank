package com.qinsheng.tank.entity;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/19.
 */
public class Wall extends GameObject {

    int w, h;

    public Rectangle rectangle;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.rectangle = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x, y, w, h);
        graphics.setColor(c);
    }
}
