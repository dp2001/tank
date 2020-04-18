package com.qinsheng.tank;

/**
 * Created by qinsheng on 2020/4/11.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();

        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }


    }

}
