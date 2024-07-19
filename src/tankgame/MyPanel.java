package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//为了 MyPanel 不停地绘制子弹 需要将 MyPanel 实现 Runnable 子弹绘制做成线程处理
@SuppressWarnings("all")
public class MyPanel extends JPanel implements KeyListener, Runnable {

    //定义我的坦克
    Hero hero = null;
    //定义敌方坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSizi = 3;
    //定义存放炸弹集合 当敌人坦克被击中时添加炸弹
    Vector<Bomb> bombs = new Vector<>();
    //定义炸弹爆炸用到的三张图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        hero = new Hero(100, 100);  //初始化自己的坦克
        hero.setSpeed(5);  //设置我方坦克行驶速度
        //初始化一个敌人的坦克
        for (int i = 0; i < enemyTankSizi; i++) {
            //创建一个敌人的坦克
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            //设置敌人坦克方向
            enemyTank.setDirect(2);
            //给enemyTank 加入一颗子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            //加入enemyTank的Vector成员
            enemyTank.shots.add(shot);
            //启动 shot 对象
            new Thread(shot).start();
            //加入enemyTanks的Vector成员
            enemyTanks.add(enemyTank);
        }
        //初始化炸弹爆炸图片对象
        image1 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/IM1.gif"));
        image2 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/IM2.gif"));
        image3 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/IM3.gif"));
    }



    //判断我方子弹是否击中敌人坦克
    public void hitTank(Shot s, EnemyTank enemyTank) {

        switch (enemyTank.getDirect()) {//根据敌人坦克的方向 ，判断坦克坐标占用面积
            case 0: //坦克的方向 向上
            case 2: //坦克的方向 向下
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 40 &&
                        s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 60
                ) {
                    //敌方坦克生命消亡
                    enemyTank.islive = false;
                    //我方子弹生命消亡
                    s.islive = false;
                    //创建炸弹对象 添加到集合中
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);

                }
                break;
            case 1: //坦克的方向 向左
            case 3: //坦克的方向 向右
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 60 &&
                        s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 40
                ) {
                    enemyTank.islive = false;
                    s.islive = false;

                }
                break;
        }

    }

    ;


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);  //填充矩形默认蓝色
        //画我方坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);

        //画出我方坦克子弹
        if (hero.shot != null && hero.shot.islive) {
            g.fill3DRect(hero.shot.getX(), hero.shot.getY(), 2, 2, false);

        }
        //如果bombs 集合中有对象 就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前炸弹的生命值画出对应图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60,60,this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y,50, 50, this);
            }else {
                g.drawImage(image3, bomb.x, bomb.y, 40, 40, this);
            }
            //让炸弹生命值减少
            bomb.lifeDown();
            //
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }



        //画出敌方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //从容器中取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前敌人坦克是否存活
            if (enemyTank.islive) { //当敌人的坦克存活 再画出敌人坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //enemyTank.setDirect(2);  //设置敌方坦克初始化方向
                //画出 enemyTank 所有子弹
                if (enemyTank.islive) {//当敌人坦克活着再画出

                    for (int j = 0; j < enemyTank.shots.size(); j++) {
                        //取出子弹
                        Shot shot = enemyTank.shots.get(j);
                        //绘制子弹
                        if (shot.islive) {
                            g.fill3DRect(shot.getX(), shot.getY(), 2, 2, false);
                        } else {
                            //从集合中移除敌人坦克
                            enemyTank.shots.remove(shot);
                        }

                    }
                }
            }
        }


    }
    //编写方法 画出坦克

    /**
     * @param x      坦克左上角x坐标
     * @param y      坦克左上角y坐标
     * @param g      画笔
     * @param direct 坦克的方向（上下左右）
     * @param type   坦克类型（我方敌方）
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型坦克，区分颜色
        switch (type) {
            case 0: //玩家坦克
                g.setColor(Color.cyan);
                break;
            case 1: //敌方坦克
                g.setColor(Color.YELLOW);
                break;
        }

        //根据坦克方向来，绘制坦克
        switch (direct) {
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);  //画出坦克左轮子边
                g.fill3DRect(x + 30, y, 10, 60, false);  //画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);  //画出坦克的机身
                g.drawOval(x + 10, y + 20, 20, 20);  //还出坦克圆盖
                g.drawLine(x + 20, y + 30, x + 20, y);  //画出炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x, y, 60, 10, false);  //画出坦克左轮子边
                g.fill3DRect(x, y + 30, 60, 10, false);  //画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);  //画出坦克的机身
                g.drawOval(x + 20, y + 10, 20, 20);  //还出坦克圆盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20);  //画出炮筒
                break;
            case 2: //表示向下 与向上对调炮筒方向
                g.fill3DRect(x, y, 10, 60, false);  //画出坦克左轮子边
                g.fill3DRect(x + 30, y, 10, 60, false);  //画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);  //画出坦克的机身
                g.drawOval(x + 10, y + 20, 20, 20);  //还出坦克圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);  //画出炮筒
                break;

            case 3: //表示向左 与向右对调炮筒方向
                g.fill3DRect(x, y, 60, 10, false);  //画出坦克左轮子边
                g.fill3DRect(x, y + 30, 60, 10, false);  //画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);  //画出坦克的机身
                g.drawOval(x + 20, y + 10, 20, 20);  //还出坦克圆盖
                g.drawLine(x + 30, y + 20, x, y + 20);  //画出炮筒

        }


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            //设置坦克方向向上
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            //设置坦克方向向右
            hero.setDirect(1);
            hero.moveRiht();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            //设置坦克方向向下
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            //设置坦克方向向左
            hero.setDirect(3);
            hero.moveLeft();
        }
        //按下J键 我方坦克开火
        if (e.getKeyCode() == KeyEvent.VK_J) {

            hero.shotEnmyTank();
        }

        //让面板重绘
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //判断我方子弹是否击中了敌人的坦克
            if (hero.shot != null && hero.shot.islive) {//前提是 我方子弹还存活 才能打中  没按J键之前shot为 null

                //不知道要打中哪个坦克 所以要遍历取出敌人坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(hero.shot, enemyTank);
                }

            }

            //重绘
            this.repaint();

        }

    }
}
