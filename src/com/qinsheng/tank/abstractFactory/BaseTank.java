package com.qinsheng.tank.abstractFactory;

import com.qinsheng.tank.TankFrame;
import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/17.
 */
public abstract class BaseTank {

    public Rectangle rectangle = new Rectangle();

    //敌方坦克还是我方坦克
    public Group group = Group.BAD;

    //子弹坐标

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int x, y;

    //坦克是否活着
    public boolean living = true;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //阵亡
    public void die() {
        this.living = false;
    }

    public abstract void paint(Graphics graphics);

}
