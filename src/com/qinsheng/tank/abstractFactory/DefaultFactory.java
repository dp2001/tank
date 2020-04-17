package com.qinsheng.tank.abstractFactory;

import com.qinsheng.tank.TankFrame;
import com.qinsheng.tank.entity.Bullet;
import com.qinsheng.tank.entity.Explode;
import com.qinsheng.tank.entity.Tank;
import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;

/**
 * Created by qinsheng on 2020/4/17.
 */
public class DefaultFactory extends GameFactory{


    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new Tank(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new Bullet(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }
}
