package com.qinsheng.tank.cor;

import com.qinsheng.tank.entity.Bullet;
import com.qinsheng.tank.entity.GameObject;
import com.qinsheng.tank.entity.Wall;

/**
 * Created by qinsheng on 2020/4/19.
 */
public class BulletWallCollier implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet)o1;
            Wall w = (Wall)o2;

            if(b.rectangle.intersects(w.rectangle)) {
                b.die();
            }
        } else if(o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
