package com.qinsheng.tank.observer;

import com.qinsheng.tank.entity.Tank;

import java.util.Arrays;
import java.util.List;

public class TankFireEvent {

    private List<TankFireObserver> fireObservers = Arrays.asList(new TankFireHandler());

    Tank tank;

    public Tank getSource(){
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
        for(TankFireObserver o : fireObservers) {
            o.actionOnFire(this);
        }
    }

}
