package com.qinsheng.tank.observer;

import com.qinsheng.tank.entity.Tank;

public class TankFireEvent {

    Tank tank;

    public Tank getSource(){
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }

}
