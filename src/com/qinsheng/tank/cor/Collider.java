package com.qinsheng.tank.cor;

import com.qinsheng.tank.GameObject;

/**
 * Created by qinsheng on 2020/4/19.
 */
public interface Collider {

    boolean collide(GameObject o1, GameObject o2);

}
