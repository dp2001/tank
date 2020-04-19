package com.qinsheng.tank.decorator;

import com.qinsheng.tank.entity.GameObject;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/19.
 */
public class TailDecorator extends GameObjectDecorator {

    public TailDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics graphics) {
        this.x = gameObject.x;
        this.y = gameObject.y;
        gameObject.paint(graphics);

        Color c = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.drawLine(gameObject.x, gameObject.y, gameObject.x + getWidth(), gameObject.y + getHeight());
        graphics.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return super.gameObject.getHeight();
    }
}
