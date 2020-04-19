package com.qinsheng.tank.cor;

import com.qinsheng.tank.entity.GameObject;
import com.qinsheng.tank.entity.Tank;
import com.qinsheng.tank.entity.Wall;

/**
 * Created by qinsheng on 2020/4/19.
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank)o1;
            Wall wall = (Wall)o2;

            if(tank.getRectangle().intersects(wall.rectangle)) {
                tank.back();
            }
        } else if(o2 instanceof Tank && o1 instanceof Wall) {
            return collide(o2, o1);
        }
         return true;
    }
}
