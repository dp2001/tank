package com.qinsheng.tank.abstractFactory;

import com.qinsheng.tank.TankFrame;
import com.qinsheng.tank.list.Dir;
import com.qinsheng.tank.list.Group;

/**
 * Created by qinsheng on 2020/4/17.
 */
public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);



}
