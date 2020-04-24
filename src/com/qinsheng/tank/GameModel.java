package com.qinsheng.tank;

import com.qinsheng.tank.cor.ColliderChain;
import com.qinsheng.tank.entity.GameObject;
import com.qinsheng.tank.entity.Tank;
import com.qinsheng.tank.entity.Wall;
import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;
import com.qinsheng.tank.manager.PropertyManager;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinsheng on 2020/4/18.
 *
 * 负责Model和View的分离，负责和TankFrame打交道，负责处理Model的创建和显示
 * 对TankFrame，相当于门面模式
 * 对model来说，相当于调停者模式 -- 未完成，效果不明显
 */
public class GameModel {
    Tank myTank;

    //设置成为单例
    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }

    public List<GameObject> gameObjects = new ArrayList<>();

    //责任链
    ColliderChain colliderChain = new ColliderChain();

    private GameModel() {

    }

    private void init() {
        //我方坦克
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);

        int initTankCount = PropertyManager.getInt("initTankCount");

        //初始化敌方坦克
        for(int i = 0; i < initTankCount; i++) {
            new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD);
        }

        new Wall(150, 150, 60, 200);
    }

    public static GameModel getInstance(){
        return INSTANCE;
    }

    public void add(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

    //界面显示主要方法
    public void paint(Graphics graphics) {

        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.setColor(c);

        //显示我方坦克
//        myTank.paint(graphics);

        //显示所有子弹
        for(int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(graphics);
        }

        //互相碰撞
        for(int i=0; i<gameObjects.size(); i++) {
            for(int j=i+1; j<gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);

                colliderChain.collide(o1, o2);

            }
        }

    }

    public Tank getMyTank() {
        return myTank;
    }

    public void save() {
        File f = new File("tank.data");
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
            objectOutputStream.writeObject(myTank);
            objectOutputStream.writeObject(gameObjects);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        File f = new File("tank.data");
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(f));
            myTank = (Tank)objectInputStream.readObject();
            gameObjects = (List)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
