package com.qinsheng.tank.cor;


import com.qinsheng.tank.GameObject;
import com.qinsheng.tank.entity.Bullet;
import com.qinsheng.tank.entity.Tank;

/**
 * Created by qinsheng on 2020/4/19.
 */
public class TankTankCollier implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank) {
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;

            if(tank1.getRectangle().intersects(tank2.getRectangle())) {
                tank1.back();
                tank2.back();
                return true;
            }

        } else if(o2 instanceof Bullet && o1 instanceof Tank) {
            collide(o2, o1);
        }
        return true;
    }

}
