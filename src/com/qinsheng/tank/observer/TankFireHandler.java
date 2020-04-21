package com.qinsheng.tank.observer;

public class TankFireHandler implements TankFireObserver {

    @Override
    public void actionOnFire(TankFireEvent event) {
        event.getSource().fire();
    }
}
