package com.qinsheng.tank.strategy;

import com.qinsheng.tank.GameModel;
import com.qinsheng.tank.decorator.RectDecorator;
import com.qinsheng.tank.decorator.TailDecorator;
import com.qinsheng.tank.entity.Bullet;
import com.qinsheng.tank.entity.GameObject;
import com.qinsheng.tank.entity.Tank;
import com.qinsheng.tank.list.Group;
import com.qinsheng.tank.util.Audio;

/**
 * Created by qinsheng on 2020/4/16.
 */
public class DefaultFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank tank) {

        int bulletX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        //装饰者模式
        //GameModel.getInstance().add(new TailDecorator(new RectDecorator(new Bullet(bulletX, bulletY, tank.dir, tank.group))));

        new Bullet(bulletX, bulletY, tank.dir, tank.group);

        if(tank.getGroup() == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.mav"));
    }
}
