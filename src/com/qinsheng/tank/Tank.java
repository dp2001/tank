package com.qinsheng.tank;

import java.awt.*;
import java.util.Random;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Tank {

    //坦克坐标
    private int x, y;
    //坦克方向
    private Dir dir = Dir.DOWN;
    //坦克速度
    private static final int SPEED = 1;
    //坦克是否活着
    private boolean living = true;
    //坦克是否移动
    private boolean moving = true;
    //敌方坦克还是我方坦克
    private Group group = Group.BAD;

    //坦克的宽度和高度
    public static int WIDTH = ResourceManager.goodTankD.getWidth();
    public static int HEIGHT = ResourceManager.goodTankD.getHeight();

    private Random random = new Random();

    //tankfrome 引用
    private TankFrame tankFrame = null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

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

    public Dir getDir() {
        return dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    //构造方法
    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    //坦克的显示主方法
    public void paint(Graphics graphics){
        //如果敌方坦克死了，从敌方坦克列表中移除
        if(!living) {
            tankFrame.tankList.remove(this);
        }

        //根据方法显示不同的坦克图片
        switch (dir) {
            case LEFT:
                graphics.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankL : ResourceManager.badTankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankR : ResourceManager.badTankR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankD : ResourceManager.badTankR, x, y, null);
                break;
            case UP:
                graphics.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankU : ResourceManager.badTankR, x, y, null);
                break;
        }
        move();
    }

    //发射子弹，给子弹列表增加一颗子弹，位置从坦克中心点发出
    public void fire() {
        int bulletX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tankFrame.bulletList.add(new Bullet(bulletX, bulletY, this.dir, this.group, this.tankFrame));
    }

    //坦克的移动方法
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

        //敌方坦克的随机开火
        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        //敌方坦克随机变换方向
        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        //边界检测
        boundsCheck();
    }

    private void boundsCheck() {
        if(this.x < 0) x = 0;
        if(this.y < 30) y = 30;
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    //随机方向
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    //阵亡
    public void die() {
        this.living = false;
    }

}
