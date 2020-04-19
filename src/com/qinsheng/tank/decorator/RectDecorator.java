package com.qinsheng.tank.decorator;

import com.qinsheng.tank.entity.GameObject;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/19.
 */
public class RectDecorator extends GameObjectDecorator {

    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics graphics) {
        this.x = gameObject.x;
        this.y = gameObject.y;
        gameObject.paint(graphics);

        Color c = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.drawRect(gameObject.x, gameObject.y, getWidth() + 2, getHeight() + 2);
        graphics.setColor(c);
    }

    @Override
    public int getWidth() {
        return gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return gameObject.getHeight();
    }
}
