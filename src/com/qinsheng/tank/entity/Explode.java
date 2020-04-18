package com.qinsheng.tank.entity;

import com.qinsheng.tank.GameModel;
import com.qinsheng.tank.GameObject;
import com.qinsheng.tank.util.Audio;
import com.qinsheng.tank.manager.ResourceManager;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Explode extends GameObject {

    //爆炸图片的宽度和高度
    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    public static int HEIGHT = ResourceManager.explodes[0].getHeight();

    //爆炸坐标
    private int x, y;

    private int step = 0;

    public GameModel gameModel = null;

    //构造方法
    public Explode(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;

        //显示音效
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    //爆炸显示主方法，显示多张图片以达到动画效果，显示完成后，自动从爆炸图片列表删除
    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(ResourceManager.explodes[step++], x, y, null);
        if(step >= ResourceManager.explodes.length){
            gameModel.remove(this);
        }
    }
}
