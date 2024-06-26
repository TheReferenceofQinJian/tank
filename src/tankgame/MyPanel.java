package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;


public class MyPanel extends JPanel implements KeyListener {

    //定义我的坦克
    Hero hero = null;
    //定义敌方坦克
    Vector<EnemyTank> enemyTenks = new Vector<>();
    int enemyTenksSizis = 3;
    public MyPanel(){
        hero = new Hero(100, 100);  //初始化自己的坦克
        hero.setSpeed(5);  //甚至我方坦克行驶速度
        for (int i = 0; i < enemyTenksSizis; i++) {
            enemyTenks.add(new EnemyTank(100*(i+1), 0));
        }


    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);  //填充矩形默认蓝色
        //画我方坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        //画出敌方坦克
        for (int i = 0; i < enemyTenks.size(); i++) {
           EnemyTank enemyTank = enemyTenks.get(i); //从容器中取出坦克
           enemyTank.setDirect(2);  //设置敌方坦克初始化方向
           drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(),1);
        }


    }
    //编写方法 画出坦克

    /**
     *
     * @param x   坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direct 坦克的方向（上下左右）
     * @param type  坦克类型（我方敌方）
     */
    public void drawTank(int x, int y, Graphics g,int direct, int type){
        //根据不同类型坦克，区分颜色
        switch (type){
            case 0: //玩家坦克
                g.setColor(Color.cyan);
                break;
            case 1: //敌方坦克
                g.setColor(Color.YELLOW);
                break;
        }

        //根据坦克方向来，绘制坦克
        switch (direct){
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);  //画出坦克左轮子边
                g.fill3DRect(x+30, y, 10, 60,false );  //画出坦克右边轮子
                g.fill3DRect(x+10, y+10, 20, 40, false);  //画出坦克的机身
                g.drawOval(x+10,y+20, 20, 20);  //还出坦克圆盖
                g.drawLine(x+20, y+30,x+20, y );  //画出炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x, y, 60, 10, false);  //画出坦克左轮子边
                g.fill3DRect(x, y+30, 60, 10,false );  //画出坦克右边轮子
                g.fill3DRect(x+10, y+10, 40, 20, false);  //画出坦克的机身
                g.drawOval(x+20,y+10, 20, 20);  //还出坦克圆盖
                g.drawLine(x+30, y+20,x+60, y+20 );  //画出炮筒
                break;
            case 2: //表示向下 与向上对调炮筒方向
                g.fill3DRect(x, y, 10, 60, false);  //画出坦克左轮子边
                g.fill3DRect(x+30, y, 10, 60,false );  //画出坦克右边轮子
                g.fill3DRect(x+10, y+10, 20, 40, false);  //画出坦克的机身
                g.drawOval(x+10,y+20, 20, 20);  //还出坦克圆盖
                g.drawLine(x+20, y+30,x+20, y+60 );  //画出炮筒
                break;

            case 3: //表示向左 与向右对调炮筒方向
                g.fill3DRect(x, y, 60, 10, false);  //画出坦克左轮子边
                g.fill3DRect(x, y+30, 60, 10,false );  //画出坦克右边轮子
                g.fill3DRect(x+10, y+10, 40, 20, false);  //画出坦克的机身
                g.drawOval(x+20,y+10, 20, 20);  //还出坦克圆盖
                g.drawLine(x+30, y+20,x, y+20 );  //画出炮筒

        }


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            //设置坦克方向向上
            hero.setDirect(0);
            hero.moveUp();
            System.out.println("按下了W");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            //设置坦克方向向右
            hero.setDirect(1);
            System.out.println("按下了D");
            hero.moveRiht();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            //设置坦克方向向下
            hero.setDirect(2);
            hero.moveDown();
            System.out.println("按下了S");
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            //设置坦克方向向左
            hero.setDirect(3);
            hero.moveLeft();
            System.out.println("按下了A");
        }

        //让面板重绘
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
