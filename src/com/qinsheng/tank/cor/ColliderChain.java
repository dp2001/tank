package com.qinsheng.tank.cor;

import com.qinsheng.tank.entity.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qinsheng on 2020/4/19.
 */
public class ColliderChain implements Collider {

    private List<Collider> colliderList = new LinkedList<>();

    public ColliderChain(){
        add(new BulletTankCollier());
        add(new TankTankCollier());
        add(new BulletWallCollier());
        add(new TankWallCollider());
    }

    public void add(Collider c) {
        colliderList.add(c);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for(int i=0; i<colliderList.size(); i++) {
            if(!colliderList.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }

}
