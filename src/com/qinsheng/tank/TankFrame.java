package com.qinsheng.tank;


import com.qinsheng.tank.entity.Bullet;
import com.qinsheng.tank.entity.Explode;
import com.qinsheng.tank.entity.Tank;
import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;
import com.qinsheng.tank.manager.PropertyManager;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by qinsheng on 2020/4/11.
 */
public class TankFrame extends Frame {

    GameModel gameModel = new GameModel();


    //设置游戏界面大小
    public static final int GAME_WIDTH = PropertyManager.getInt("gameWidth"), GAME_HEIGHT = PropertyManager.getInt("gameHeight");

    //构造方法，设置界面属性，以及按键事件
    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //清除闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //界面显示主要方法
    @Override
    public void paint(Graphics graphics) {
        gameModel.paint(graphics);
    }


    //内部类，处理按键事件
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;

                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;

                case KeyEvent.VK_CONTROL:
                    gameModel.getMyTank().fire();
                    break;

                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            Tank myTank = gameModel.getMyTank();
            if(!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);

                if(bL) myTank.setDir(Dir.LEFT);
                if(bD) myTank.setDir(Dir.DOWN);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bU) myTank.setDir(Dir.UP);
            }
        }
    }

}
