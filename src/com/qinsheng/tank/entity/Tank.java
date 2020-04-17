package com.qinsheng.tank.entity;

import com.qinsheng.tank.abstractFactory.BaseTank;
import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;
import com.qinsheng.tank.TankFrame;
import com.qinsheng.tank.manager.PropertyManager;
import com.qinsheng.tank.manager.ResourceManager;
import com.qinsheng.tank.strategy.DefaultFireStrategy;
import com.qinsheng.tank.strategy.FireStrategy;
import com.qinsheng.tank.strategy.FourDirFireStrategy;
import com.qinsheng.tank.util.Audio;

import java.awt.*;
import java.util.Random;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Tank extends BaseTank {

    //子弹发射策略
//    FireStrategy fireStrategy;

    //坦克坐标
    public int x, y;
    //坦克方向
    public Dir dir = Dir.DOWN;
    //坦克速度
    private static final int SPEED = PropertyManager.getInt("tankSpeed");
    //坦克是否移动
    private boolean moving = false;

    //坦克范围，矩形
//    Rectangle rectangle = new Rectangle();

    //坦克的宽度和高度
    public static int WIDTH = ResourceManager.goodTankD.getWidth();
    public static int HEIGHT = ResourceManager.goodTankD.getHeight();

    private Random random = new Random();

    //tankfrome 引用
    public TankFrame tankFrame = null;

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

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    //坦克的显示主方法
    @Override
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
                graphics.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankD : ResourceManager.badTankD, x, y, null);
                break;
            case UP:
                graphics.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankU : ResourceManager.badTankU, x, y, null);
                break;
        }
        move();
    }

    //发射子弹，给子弹列表增加一颗子弹，位置从坦克中心点发出
    public void fire() {
//        if(group == Group.GOOD) {
//            String goodFireStrategyName = PropertyManager.get("goodFireStrategy").toString();
//            try {
//                //java 9后不建议使用
//                //fireStrategy = (FireStrategy) Class.forName(goodFireStrategyName).newInstance();
//                fireStrategy = (FireStrategy) Class.forName(goodFireStrategyName).getDeclaredConstructor().newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            String badFireStrategyName = PropertyManager.get("badFireStrategy").toString();
//            try {
//                //java 9后不建议使用
//                fireStrategy = (FireStrategy) Class.forName(badFireStrategyName).newInstance();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        fireStrategy.fire(this);

        int bulletX = x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tankFrame.bulletList.add(tankFrame.gameFactory.createBullet(bulletX, bulletY, this.dir, this.group, this.tankFrame));

        if(this.getGroup() == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.mav"));
    }

    //坦克的移动方法
    private void move() {
//        if(!moving) return;
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

        // update rect
        rectangle.x = this.x;
        rectangle.y = this.y;
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
