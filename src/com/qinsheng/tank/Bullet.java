package com.qinsheng.tank;

import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Created by qinsheng on 2020/4/12.
 */
public class Bullet {

    //子弹的宽度和高度
    public static int WIDTH = ResourceManager.bulletD.getWidth();
    public static int HEIGHT = ResourceManager.bulletD.getHeight();

    //子弹坐标
    private int x, y;
    //子弹方向
    private Dir dir;
    //子弹是否活着
    private boolean living = true;
    //子弹速度
    private static final int SPEED = 10;
    //敌方子弹还是我方子弹
    private Group group = Group.BAD;

    TankFrame tankFrame = null;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //构造方法
    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    //显示子弹主方法
    public void paint(Graphics graphics) {
        if(!living) {
            tankFrame.bulletList.remove(this);
        }

        switch (dir) {
            case LEFT:
                graphics.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceManager.bulletD, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceManager.bulletU, x, y, null);
                break;
        }

        move();
    }

    //子弹移动
    private void move(){
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }

        //当子弹飞出界外，标记为死亡
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    //死亡
    private void die() {
        this.living = false;
    }

    //碰撞方法，判断是否和坦克相撞，相撞同阵营坦克，不做处理。
    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;

        //TODO: 用一个rect来记录子弹的位置
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if(rect1.intersects(rect2)) {
            tank.die();
            this.die();
            int explodeX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int explodeY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tankFrame.explodes.add(new Explode(explodeX, explodeY, tankFrame));
        }
    }
}
