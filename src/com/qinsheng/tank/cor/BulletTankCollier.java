package com.qinsheng.tank.cor;


import com.qinsheng.tank.GameObject;
import com.qinsheng.tank.entity.Bullet;
import com.qinsheng.tank.entity.Tank;

/**
 * Created by qinsheng on 2020/4/19.
 */
public class BulletTankCollier implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            bullet.collideWith(tank);

            return !bullet.collideWith(tank);

//            if(bullet.group == tank.getGroup()) return;
//
//            if(bullet.rectangle.intersects(tank.rectangle)) {
//                tank.die();
//                bullet.die();
//                int explodeX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
//                int explodeY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
//                gameModel.add(new Explode(explodeX, explodeY, gameModel));
//            }
        } else if(o2 instanceof Bullet && o1 instanceof Tank) {
            collide(o2, o1);
        }

        return true;
    }

}
