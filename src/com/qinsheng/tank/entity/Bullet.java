package com.qinsheng.tank.entity;

import com.qinsheng.tank.GameModel;
import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;
import com.qinsheng.tank.TankFrame;
import com.qinsheng.tank.manager.PropertyManager;
import com.qinsheng.tank.manager.ResourceManager;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Bullet extends GameObject {

    //子弹的宽度和高度
    public static int WIDTH = ResourceManager.bulletD.getWidth();
    public static int HEIGHT = ResourceManager.bulletD.getHeight();

    public Rectangle rectangle = new Rectangle();

    //子弹方向
    private Dir dir;
    //子弹是否活着
    private boolean living = true;
    //子弹速度
    private static final int SPEED = PropertyManager.getInt("bulletSpeed");
    //敌方子弹还是我方子弹
    public Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //构造方法
    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        GameModel.getInstance().add(this);
    }

    //显示子弹主方法
    @Override
    public void paint(Graphics graphics) {
        if(!living) {
            GameModel.getInstance().remove(this);
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

    //子弹移动
    private void move(){
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
        }

        // update rect
        rectangle.x = this.x;
        rectangle.y = this.y;

        //当子弹飞出界外，标记为死亡
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    //死亡
    public void die() {
        this.living = false;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
