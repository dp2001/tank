package com.qinsheng.tank;

/**
 * Created by qinsheng on 2020/4/11.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();

        //初始化敌方坦克
        for(int i = 0; i < 5; i++) {
            tf.tankList.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, tf));
        }

        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }


    }

}
