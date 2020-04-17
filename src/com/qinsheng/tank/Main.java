package com.qinsheng.tank;

import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;
import com.qinsheng.tank.manager.PropertyManager;

/**
 * Created by qinsheng on 2020/4/11.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();

//        GameFactory defaultFactory = new DefaultFactory();

        int initTankCount = PropertyManager.getInt("initTankCount");

        //初始化敌方坦克
        for(int i = 0; i < initTankCount; i++) {
            tf.tankList.add(tf.gameFactory.createTank(50 + i*80, 200, Dir.DOWN, Group.BAD, tf));
        }

        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }


    }

}
