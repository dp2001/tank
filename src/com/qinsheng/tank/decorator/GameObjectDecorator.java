package com.qinsheng.tank.decorator;

import com.qinsheng.tank.entity.GameObject;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/19.
 */
public abstract class GameObjectDecorator extends GameObject {

    GameObject gameObject;

    public GameObjectDecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void paint(Graphics graphics) {
        gameObject.paint(graphics);
    }

}
