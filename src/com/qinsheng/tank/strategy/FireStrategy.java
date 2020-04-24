package com.qinsheng.tank.strategy;

import com.qinsheng.tank.entity.Tank;

import java.io.Serializable;

/**
 * Created by qinsheng on 2020/4/16.
 */
public interface FireStrategy extends Serializable{

    void fire(Tank tank);
}
