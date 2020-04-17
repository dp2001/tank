package com.qinsheng.tank.abstractFactory;

import com.qinsheng.tank.entity.Bullet;
import com.qinsheng.tank.entity.Tank;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/17.
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics graphics);

    public abstract void collideWith(BaseTank tank);

}
