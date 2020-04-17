package com.qinsheng.tank.abstractFactory;

import com.qinsheng.tank.TankFrame;
import com.qinsheng.tank.manager.ResourceManager;
import com.qinsheng.tank.util.Audio;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/17.
 */
public class RectExplode extends BaseExplode {

    //爆炸图片的宽度和高度
    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    public static int HEIGHT = ResourceManager.explodes[0].getHeight();

    //爆炸坐标
    private int x, y;

    TankFrame tankFrame = null;

    private int step = 0;

    //构造方法
    public RectExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        //显示音效
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    //爆炸显示主方法，显示多张图片以达到动画效果，显示完成后，自动从爆炸图片列表删除
    @Override
    public void paint(Graphics graphics) {
//        graphics.drawImage(ResourceManager.explodes[step++], x, y, null);

        Color color = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, 10*step, 10*step);
        step++;
        graphics.setColor(color);

//        if(step >= ResourceManager.explodes.length){
        if(step >= 15) {
            tankFrame.explodes.remove(this);
        }
    }
}
