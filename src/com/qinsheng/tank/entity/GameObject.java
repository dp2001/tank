package com.qinsheng.tank.entity;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/18.
 * 所有游戏物体的父类
 */
public abstract class GameObject {

    int x, y;

    public abstract void paint(Graphics graphics);

}
