package com.qinsheng.tank;

import com.qinsheng.tank.entity.Bullet;
import com.qinsheng.tank.entity.Explode;
import com.qinsheng.tank.entity.Tank;
import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;
import com.qinsheng.tank.manager.PropertyManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinsheng on 2020/4/18.
 */
public class GameModel {

    //我方坦克
    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
    //子弹
    public List<Bullet> bulletList = new ArrayList<>();
    //敌方坦克
    public List<Tank> tankList = new ArrayList<>();
    //爆炸
    public List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        int initTankCount = PropertyManager.getInt("initTankCount");

        //初始化敌方坦克
        for(int i = 0; i < initTankCount; i++) {
            this.tankList.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    //界面显示主要方法
    public void paint(Graphics graphics) {

        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量" + bulletList.size(), 10, 60);
        graphics.drawString("敌人的数量" + tankList.size(), 10, 80);
        graphics.drawString("爆炸的数量" + explodes.size(), 10, 100);
        graphics.setColor(c);

        //显示我方坦克
        myTank.paint(graphics);

        //显示所有子弹
        for(int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(graphics);
        }

        //显示所有敌方坦克
        for(int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(graphics);
        }

        //显示所有爆炸现象
        for(int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(graphics);
        }

        //判断所有子弹和敌方坦克是否相撞
        for(int i = 0; i < bulletList.size(); i++) {
            for(int j = 0; j < tankList.size(); j++) {
                bulletList.get(i).collideWith(tankList.get(j));
            }
        }

    }

    public Tank getMyTank() {
        return myTank;
    }

}
