package tankgame;


import java.util.Vector;

//自己的坦克
public class Hero extends Tank {

    //定义一个Shot对象表示设计线程
    Shot shot = null;   //表示射击行为

    //为了发射多颗子弹
    Vector<Shot>shots =  new Vector<>();

    public Hero(int x, int y){

        super(x, y);
    }

    //射击
    public void shotEnmyTank(){

        //控制子弹数量
        if (shots.size() == 5) {
            return;
        }

        //创建Shot对象 根据Hero对象的位置和方向来创建Shot
        switch (getDirect()){
            case 0: //向上
                shot = new Shot(getX()+20, getY(),0);
                break;
            case 1: //向右
                shot = new Shot(getX()+60, getY()+20,1);
                break;
            case 2: //向下
                shot = new Shot(getX()+20, getY()+60,2);
                break;
            case 3: //向左
                shot = new Shot(getX(), getY()+20,3);
                break;
        }

        //把新建shot放入集合中
        shots.add(shot);

        //启动线程
        new Thread(shot).start();
    }

}
