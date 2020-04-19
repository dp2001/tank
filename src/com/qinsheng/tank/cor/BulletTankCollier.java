package com.qinsheng.tank.cor;


import com.qinsheng.tank.entity.Explode;
import com.qinsheng.tank.entity.GameObject;
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

            //碰撞方法，判断是否和坦克相撞，相撞同阵营坦克，不做处理。
            if(bullet.group == tank.getGroup()) {
                return true;
            }

            if(bullet.rectangle.intersects(tank.getRectangle())) {
                tank.die();
                bullet.die();
                int explodeX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
                int explodeY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
                new Explode(explodeX, explodeY);
            }
        } else if(o2 instanceof Bullet && o1 instanceof Tank) {
            collide(o2, o1);
        }

        return true;
    }

}
